package sa.mhmd.nabd.modules.cms;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sa.mhmd.nabd.modules.cms.dto.ContentResponse;
import sa.mhmd.nabd.modules.cms.dto.CreateContentRequest;
import sa.mhmd.nabd.modules.cms.dto.UpdateContentRequest;
import sa.mhmd.nabd.modules.cms.dto.UploadContentResponse;
import sa.mhmd.nabd.shared.NabdResponse;
import sa.mhmd.nabd.shared.constant.ApiPaths;
import sa.mhmd.nabd.shared.enums.ContentType;

@RestController
@RequestMapping(ApiPaths.CMS.BASE)
@RequiredArgsConstructor
public class ContentControllerImpl implements ContentController {
    private final ContentApplicationService contentApplicationService;

    @Override
    @PostMapping(ApiPaths.CMS.CREATE)
    public ResponseEntity<NabdResponse<ContentResponse>> createContent(@Valid @RequestBody CreateContentRequest request) {
        return ResponseEntity.ok(NabdResponse.success(contentApplicationService.createContent(request)));
    }

    @Override
    @PutMapping(ApiPaths.CMS.UPDATE)
    public ResponseEntity<NabdResponse<ContentResponse>> updateContent(@Valid @RequestBody UpdateContentRequest request) {
        return ResponseEntity.ok(NabdResponse.success(contentApplicationService.updateContent(request)));
    }

    @PostMapping(ApiPaths.CMS.UPLOAD)
    public ResponseEntity<NabdResponse<UploadContentResponse>> uploadMedia(
            @PathVariable Long contentId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") ContentType type) {

        String mediaUrl = contentApplicationService.uploadMedia(contentId, file, type);

        return ResponseEntity.ok(NabdResponse.success(UploadContentResponse.builder().url(mediaUrl).build()));
    }

}
