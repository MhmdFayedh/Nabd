package sa.mhmd.nabd.modules.discovery.dto;

import lombok.Data;
import sa.mhmd.nabd.shared.enums.ContentType;

@Data
public class SearchRequest {
    private String query;
    private ContentType type;
    private String language;
    private String[] tags;
    private int page = 0;
    private int size = 10;
}
