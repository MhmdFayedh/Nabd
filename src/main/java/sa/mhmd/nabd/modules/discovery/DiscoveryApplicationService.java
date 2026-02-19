package sa.mhmd.nabd.modules.discovery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sa.mhmd.nabd.modules.discovery.model.ContentDocument;

interface DiscoveryApplicationService {
    ContentDocument indexContent(ContentDocument document);

    void deleteContent(Long id);

    Page<ContentDocument> fullTextSearch(String query, Pageable pageable);

}
