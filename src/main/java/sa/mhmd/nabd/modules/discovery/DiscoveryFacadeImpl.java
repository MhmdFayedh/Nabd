package sa.mhmd.nabd.modules.discovery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sa.mhmd.nabd.modules.cms.entity.ContentEntity;
import sa.mhmd.nabd.modules.discovery.model.ContentDocument;

@Service
@RequiredArgsConstructor
public class DiscoveryFacadeImpl implements DiscoveryFacade {
    private final DiscoveryApplicationService discoveryApplicationService;
    @Override
    public void indexContent(ContentEntity entity) {
        discoveryApplicationService.indexContent(ContentDocument.from(entity));
    }

    @Override
    public void deleteContent(Long id) {
        discoveryApplicationService.deleteContent(id);
    }
}
