package sa.mhmd.nabd.modules.cms;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sa.mhmd.nabd.modules.cms.dto.ContentResponse;
import sa.mhmd.nabd.modules.cms.dto.CreateContentRequest;
import sa.mhmd.nabd.modules.cms.dto.UpdateContentRequest;
import sa.mhmd.nabd.modules.cms.entity.ContentEntity;
import sa.mhmd.nabd.modules.discovery.DiscoveryFacade;
import sa.mhmd.nabd.shared.enums.Errors;
import sa.mhmd.nabd.shared.enums.ContentType;
import sa.mhmd.nabd.shared.exception.NabdBusinessException;


@RequiredArgsConstructor
@Service
@Slf4j
class ContentApplicationServiceImpl implements ContentApplicationService {
    private final ContentRepository contentRepository;
    private final ContentDomainService contentDomainService;
    private final ContentMapper contentMapper;
    private final MediaStorageService mediaStorageService;
    private final DiscoveryFacade discoveryFacade;

    @Override
    @Transactional
    public ContentResponse createContent(CreateContentRequest request) {
        log.info("Creating content with title: {}", request.getTitle());

        ContentEntity contentEntity = contentRepository.createContent(contentMapper.toEntity(request));
        discoveryFacade.indexContent(contentEntity);

        return contentMapper.toResponse(contentEntity);
    }

    @Override
    @Transactional
    public ContentResponse updateContent(UpdateContentRequest request) {
        log.info("Updating content with id: {}", request.getId());
        ContentEntity contentEntity = contentRepository.updateContent(contentMapper.toUpdateEntity(request));
        discoveryFacade.indexContent(contentEntity);
        return contentMapper.toResponse(contentEntity);
    }

    @Override
    @Transactional
    public String uploadMedia(Long contentId, MultipartFile file, ContentType type) {
      try {
          log.info("Uploading media for content {}: {}", contentId, file.getOriginalFilename());

          contentDomainService.validateFile(file, type);
          log.info("File validation passed");

          String fileName = contentDomainService.generateFileName(file.getOriginalFilename(), type, contentId);
          String mediaUrl = mediaStorageService.store(file, fileName);


          ContentEntity entity = contentRepository.getContentById(contentId);

          switch (type) {
              case PODCAST -> entity.setAudioUrl(mediaUrl);
              case DOCUMENTARY -> entity.setVideoUrl(mediaUrl);
          }

          contentRepository.updateContent(entity);
          log.info("Media uploaded and saved for content {}: {}", contentId, mediaUrl);

          discoveryFacade.indexContent(entity);
          log.info("Content indexed in ES: {}", contentId);
          return mediaUrl;
      } catch (Exception e) {
          log.error("Error uploading media: {}", e.getMessage());
          throw new NabdBusinessException(Errors.MEDIA_UPLOAD_FAILED.getCode());
      }
    }
}
