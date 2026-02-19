package sa.mhmd.nabd.modules.cms;

import org.springframework.web.multipart.MultipartFile;
import sa.mhmd.nabd.modules.cms.dto.ContentResponse;
import sa.mhmd.nabd.modules.cms.dto.CreateContentRequest;
import sa.mhmd.nabd.modules.cms.dto.UpdateContentRequest;
import sa.mhmd.nabd.shared.enums.ContentType;

interface ContentApplicationService {
    ContentResponse createContent(CreateContentRequest request);

    ContentResponse updateContent(UpdateContentRequest request);

    String uploadMedia(Long contentId, MultipartFile file, ContentType type);
}
