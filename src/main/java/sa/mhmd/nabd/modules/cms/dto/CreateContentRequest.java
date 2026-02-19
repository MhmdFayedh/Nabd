package sa.mhmd.nabd.modules.cms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sa.mhmd.nabd.shared.enums.ContentType;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateContentRequest {

    // Required for both PODCAST and DOCUMENTARY
    @NotNull(message = "Content type is required")
    private ContentType type;      // PODCAST, DOCUMENTARY

    @NotBlank(message = "Title is required")
    @Size(max = 255)
    private String title;

    // You can let backend generate slug, so optional in request
    @Size(max = 255)
    private String slug;

    @Size(max = 10000)            // arbitrary length limit
    private String description;

    // Optional: if null, default to DRAFT in service
    @Size(max = 32)
    private String status;

    // Optional: default to "en" or your default language
    @Size(max = 10)
    private String language;

    // Optional: comma-separated or JSON string
    private String tags;

    // Typically null on create; set when publishing
    private LocalDateTime publishedAt;

    // Podcast-specific (used when type == PODCAST)
    private String audioUrl;
    private Integer durationSeconds;
    private Integer episodeNumber;
    private Integer seasonNumber;
    private Boolean explicitContent;
    private String rssGuid;

    // Documentary-specific (used when type == DOCUMENTARY)
    private String videoUrl;
    private String thumbnailUrl;
}
