package sa.mhmd.nabd.modules.cms;

import sa.mhmd.nabd.modules.cms.dto.UpdateContentRequest;
import sa.mhmd.nabd.modules.cms.entity.ContentEntity;

interface ContentRepository {
    ContentEntity createContent(ContentEntity contentEntity);
    ContentEntity updateContent(ContentEntity contentEntity);
    ContentEntity getContentById(Long id);
}
