package sa.mhmd.nabd.modules.cms.dto;

import lombok.Builder;
import lombok.Data;
import sa.mhmd.nabd.shared.enums.ContentType;

import java.time.LocalDateTime;

@Data
@Builder
public class ContentResponse {
    private Long id;
    private ContentType type;

    private String title;
    private String slug;
    private String description;
    private String status;
    private String language;
    private String tags;

    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String audioUrl;
    private Integer durationSeconds;
    private Integer episodeNumber;
    private Integer seasonNumber;
    private Boolean explicitContent;
    private String rssGuid;

    private String videoUrl;
    private String thumbnailUrl;
}
