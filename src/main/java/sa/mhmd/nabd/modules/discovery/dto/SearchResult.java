package sa.mhmd.nabd.modules.discovery.dto;

import lombok.Data;
import sa.mhmd.nabd.shared.enums.ContentType;

@Data
public class SearchResult {
    private Long id;
    private String title;
    private String slug;
    private String description;
    private ContentType type;
    private String thumbnailUrl;
    private String mediaUrl;
    private Float score;
}
