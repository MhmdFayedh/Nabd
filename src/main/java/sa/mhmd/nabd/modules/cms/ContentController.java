package sa.mhmd.nabd.modules.cms;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import sa.mhmd.nabd.modules.cms.dto.ContentResponse;
import sa.mhmd.nabd.modules.cms.dto.CreateContentRequest;
import sa.mhmd.nabd.modules.cms.dto.UpdateContentRequest;
import sa.mhmd.nabd.modules.cms.dto.UploadContentResponse;
import sa.mhmd.nabd.shared.NabdResponse;
import sa.mhmd.nabd.shared.enums.ContentType;
@Tag(name = "Content Management System", description = "Manage Content")
public interface ContentController {
    @Operation(summary = "Create content", description = "Create new content")
    @ApiResponse(responseCode = "200", description = "Content Created successfully")
    @ApiResponse(responseCode = "404", description = "Content not found")
    ResponseEntity<NabdResponse<ContentResponse>> createContent(CreateContentRequest request);

    @Operation(summary = "Update content", description = "Update existing content")
    @ApiResponse(responseCode = "200", description = "Content updated successfully")
    @ApiResponse(responseCode = "404", description = "Content not found")
    ResponseEntity<NabdResponse<ContentResponse>> updateContent(UpdateContentRequest request);

    @Operation(summary = "Update content", description = "Upload media to an existing content")
    @ApiResponse(responseCode = "200", description = "Upload media successfully")
    @ApiResponse(responseCode = "404", description = "Content not found")
    ResponseEntity<NabdResponse<UploadContentResponse>> uploadMedia(Long contentId, MultipartFile file, ContentType type);
}
