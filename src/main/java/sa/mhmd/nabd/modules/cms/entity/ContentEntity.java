package sa.mhmd.nabd.modules.cms.entity;

import jakarta.persistence.*;
import lombok.Data;
import sa.mhmd.nabd.shared.enums.ContentType;

import java.time.LocalDateTime;

@Entity
@Table(name = "content")
@Data
public class ContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ContentType type;
    @Column(nullable = false, length = 255)
    private String title;
    @Column(nullable = false, unique = true, length = 255)
    private String slug;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(length = 32)
    private String status;
    @Column(length = 10)
    private String language;
    @Column(columnDefinition = "TEXT")
    private String tags;
    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Column(length = 512)
    private String audioUrl;
    private Integer durationSeconds;
    private Integer episodeNumber;
    private Integer seasonNumber;
    private Boolean explicitContent;
    @Column(length = 255)
    private String rssGuid;
    @Column(length = 512)
    private String videoUrl;
    @Column(length = 512)
    private String thumbnailUrl;
}
