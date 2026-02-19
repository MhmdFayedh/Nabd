package sa.mhmd.nabd.modules.discovery;

import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.NativeQuery;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;
import sa.mhmd.nabd.modules.discovery.model.ContentDocument;
import sa.mhmd.nabd.shared.enums.Errors;
import sa.mhmd.nabd.shared.exception.NabdBusinessException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
class DiscoveryApplicationServiceImpl implements DiscoveryApplicationService {
    private final DiscoveryRepository discoveryRepository;
    private final ElasticsearchOperations elasticsearchOperations;


    // CMS calls these methods
    @Override
    public ContentDocument indexContent(ContentDocument document) {
        log.info("Indexing content: {}", document.getTitle());
        return discoveryRepository.save(document);
    }

    @CacheEvict(value = "search-results", allEntries = true)
    public void deleteContent(Long id) {
        log.info("Deleting content from ES: {}", id);
        discoveryRepository.deleteById(String.valueOf(id));
    }


    @Override
    @Cacheable(value = "search-results",
            key = "#query + '_' + #pageable.pageNumber + '_' + #pageable.pageSize",
            condition = "#query != null && #query.trim().length() > 0",
            unless = "#result.isEmpty")
    public Page<ContentDocument> fullTextSearch(String query, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            throw new NabdBusinessException(Errors.CONTENT_NOT_FOUND.getCode());
        }

        String safeQuery = query.trim()
                .replaceAll("[+\\-&&\\|\\||!\\(\\)\\{\\}\\[\\]\\^\\\"\\*\\?\\:\\\\\\/]", " ")
                .replaceAll("\\s+", " ")
                .trim();

        String jsonQuery = String.format("""
        {
            "multi_match": {
                "query": "%s",
                "fields": ["title^3", "content^2", "tags^1.5", "description^1"],
                "type": "best_fields"
            }
        }
        """, safeQuery);

        StringQuery searchQuery = new StringQuery(jsonQuery);
        searchQuery.setPageable(pageable);

        SearchHits<ContentDocument> searchHits = elasticsearchOperations.search(searchQuery, ContentDocument.class);

        List<ContentDocument> content = searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();

        return new PageImpl<>(content, pageable, searchHits.getTotalHits());
    }


}
