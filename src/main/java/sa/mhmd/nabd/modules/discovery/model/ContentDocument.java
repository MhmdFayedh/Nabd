package sa.mhmd.nabd.modules.discovery.model;

import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import sa.mhmd.nabd.modules.cms.entity.ContentEntity;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Document(indexName = "cms-content")
public class ContentDocument {

    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String title;
    @Field(type = FieldType.Text, analyzer = "standard")
    private String content;
    @Field(type = FieldType.Keyword)
    private String status;
    @Field(type = FieldType.Keyword)
    private String type;
    @Field(type = FieldType.Keyword)
    private String[] tags;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime createdAt;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime updatedAt;
    // Factory method from JPA entity
    public static ContentDocument from(ContentEntity entity) {
        ContentDocument doc = new ContentDocument();
        doc.setId(String.valueOf(entity.getId()));
        doc.setTitle(entity.getTitle());
        doc.setContent(entity.getDescription());
        doc.setStatus(entity.getStatus());
        doc.setType(String.valueOf(entity.getType()));
        doc.setTags(new String[]{entity.getTags()});
        doc.setCreatedAt(entity.getCreatedAt());
        doc.setUpdatedAt(entity.getUpdatedAt());
        return doc;
    }
}