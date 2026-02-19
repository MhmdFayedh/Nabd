package sa.mhmd.nabd.modules.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sa.mhmd.nabd.shared.enums.ContentType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateContentRequest {
    private Long id;
    private ContentType type;
    private String title;
    private String slug;
    private String description;
    private String status;
    private String language;
    private String tags;
    private LocalDateTime publishedAt;
    private String audioUrl;
    private Integer durationSeconds;
    private Integer episodeNumber;
    private Integer seasonNumber;
    private Boolean explicitContent;
    private String rssGuid;
    private String videoUrl;
    private String thumbnailUrl;
}
