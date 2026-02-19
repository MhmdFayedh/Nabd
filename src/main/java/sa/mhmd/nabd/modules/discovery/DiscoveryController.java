package sa.mhmd.nabd.modules.discovery;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import sa.mhmd.nabd.modules.discovery.dto.SearchResponse;
import sa.mhmd.nabd.shared.NabdResponse;
@Tag(name = "Discover Content System", description = "Discover contents")
public interface DiscoveryController {
    @Operation(summary = "Create content", description = "Search Contents")
    @ApiResponse(responseCode = "200", description = "Content Created successfully")
    @ApiResponse(responseCode = "404", description = "Content not found")
    ResponseEntity<NabdResponse<SearchResponse>> search(String q, int page, int size);
}
