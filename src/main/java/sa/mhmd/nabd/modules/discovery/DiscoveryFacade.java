package sa.mhmd.nabd.modules.discovery;

import sa.mhmd.nabd.modules.cms.entity.ContentEntity;

public interface DiscoveryFacade {
    void indexContent(ContentEntity entity);
    void deleteContent(Long id);
}
