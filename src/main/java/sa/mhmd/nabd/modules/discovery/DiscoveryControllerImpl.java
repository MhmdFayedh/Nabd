package sa.mhmd.nabd.modules.discovery;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sa.mhmd.nabd.modules.discovery.dto.SearchResponse;
import sa.mhmd.nabd.modules.discovery.dto.SearchResult;
import sa.mhmd.nabd.modules.discovery.model.ContentDocument;
import sa.mhmd.nabd.shared.NabdResponse;
import sa.mhmd.nabd.shared.constant.ApiPaths;
import sa.mhmd.nabd.shared.enums.ContentType;

import java.util.List;

@Data
@RestController
@RequestMapping(ApiPaths.DISCOVERY.BASE)
@RequiredArgsConstructor
public class DiscoveryControllerImpl implements DiscoveryController {

    private final DiscoveryApplicationService discoveryApplicationService;

    @Override
    @GetMapping(ApiPaths.DISCOVERY.SEARCH)
    public ResponseEntity<NabdResponse<SearchResponse>> search(@RequestParam(required = false) String q,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ContentDocument> results = discoveryApplicationService.fullTextSearch(q, pageable);

        List<SearchResult> searchResults = results.getContent().stream()
                .map(this::toSearchResult)
                .toList();

        SearchResponse response = SearchResponse.builder()
                .results(searchResults)
                .totalHits(results.getTotalElements())
                .page(results.getNumber())
                .size(results.getSize())
                .hasNext(results.hasNext())
                .build();
        return ResponseEntity.ok(NabdResponse.success(response));
    }

    private SearchResult toSearchResult(ContentDocument doc) {
        SearchResult result = new SearchResult();
        result.setId(Long.valueOf(doc.getId()));
        result.setTitle(doc.getTitle());
        result.setDescription(doc.getContent());
        result.setType(doc.getType() != null ? ContentType.valueOf(doc.getType()) : null);
        return result;
    }
}

