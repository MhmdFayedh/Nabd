package sa.mhmd.nabd.modules.cms;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sa.mhmd.nabd.shared.enums.Errors;
import sa.mhmd.nabd.shared.enums.ContentType;
import sa.mhmd.nabd.shared.exception.NabdBusinessException;

import java.time.Instant;
import java.util.UUID;

@Service
class ContentDomainService {
    public void validateFile(MultipartFile file, ContentType type) throws BadRequestException {
        if (file.isEmpty()) {
            throw new NabdBusinessException(Errors.FILE_EMPTY.getCode());
        }

        String contentType = file.getContentType();
        if (type == ContentType.PODCAST && !contentType.startsWith("audio/")) {
            throw new NabdBusinessException(Errors.PODCAST_AUDIO_ONLY.getCode());
        }
        if (type == ContentType.DOCUMENTARY && !contentType.startsWith("video/")) {
            throw new NabdBusinessException(Errors.DOCUMENTARY_VIDEO_ONLY.getCode());
        }

        if (file.getSize() > 500 * 1024 * 1024) { // 500MB
            throw new NabdBusinessException(Errors.FILE_TOO_LARGE.getCode());
        }
    }

    public String getExtension(String fileName) {
        if (fileName == null) {
            return "";
        }

        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1).toLowerCase();
        }

        return "";
    }

    public String generateFileName(String originalName, ContentType type, Long contentId) {
        String extension = getExtension(originalName);
        String timestamp = Instant.now().toString().replaceAll(":", "-");
        return String.format("media/%s/%d-%s-%s.%s",
                type.name().toLowerCase(), contentId, timestamp, UUID.randomUUID(), extension);
    }
}
