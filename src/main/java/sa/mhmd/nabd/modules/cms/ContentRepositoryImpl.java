package sa.mhmd.nabd.modules.cms;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import sa.mhmd.nabd.modules.cms.entity.ContentEntity;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
@Data
class ContentRepositoryImpl implements ContentRepository {
    private final ContentRepositoryJpa contentRepositoryJpa;
    private final ContentMapper contentMapper;

    @Override
    public ContentEntity createContent(ContentEntity contentEntity) {
        contentEntity.setId(null);
        return contentRepositoryJpa.save(contentEntity);
    }

    @Override
    public ContentEntity updateContent(ContentEntity request) {
        ContentEntity existing = contentRepositoryJpa.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Content not found with id: "));

        if (request.getType() != null) {
            existing.setType(request.getType());
        }
        if (request.getTitle() != null) {
            existing.setTitle(request.getTitle());
        }
        if (request.getSlug() != null) {
            existing.setSlug(request.getSlug());
        }
        if (request.getDescription() != null) {
            existing.setDescription(request.getDescription());
        }
        if (request.getStatus() != null) {
            existing.setStatus(request.getStatus());
        }
        if (request.getLanguage() != null) {
            existing.setLanguage(request.getLanguage());
        }
        if (request.getTags() != null) {
            existing.setTags(request.getTags());
        }
        if (request.getPublishedAt() != null) {
            existing.setPublishedAt(request.getPublishedAt());
        }
        if (request.getAudioUrl() != null) {
            existing.setAudioUrl(request.getAudioUrl());
        }
        if (request.getDurationSeconds() != null) {
            existing.setDurationSeconds(request.getDurationSeconds());
        }
        if (request.getEpisodeNumber() != null) {
            existing.setEpisodeNumber(request.getEpisodeNumber());
        }
        if (request.getSeasonNumber() != null) {
            existing.setSeasonNumber(request.getSeasonNumber());
        }
        if (request.getExplicitContent() != null) {
            existing.setExplicitContent(request.getExplicitContent());
        }
        if (request.getRssGuid() != null) {
            existing.setRssGuid(request.getRssGuid());
        }
        if (request.getVideoUrl() != null) {
            existing.setVideoUrl(request.getVideoUrl());
        }
        if (request.getThumbnailUrl() != null) {
            existing.setThumbnailUrl(request.getThumbnailUrl());
        }

        existing.setUpdatedAt(LocalDateTime.now());

        return contentRepositoryJpa.save(existing);
    }

    @Override
    public ContentEntity getContentById(Long id) {
        return contentRepositoryJpa.findById(id).orElseThrow(() -> new ResourceNotFoundException("Content not found with id: " + id));
    }
}
