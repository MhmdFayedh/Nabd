package sa.mhmd.nabd.modules.cms;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@ConditionalOnProperty(name = "app.storage.type", havingValue = "local", matchIfMissing = true)
@ConfigurationProperties(prefix = "app.storage")
@Slf4j
@Data
class MediaStorageServiceImpl implements MediaStorageService {
    private String uploadDir = "uploads/media"; // application.yml: app.storage.upload-dir
    private Path uploadPath;

    @PostConstruct
    public void init() {
        // Derive uploadPath from uploadDir if not explicitly set
        if (uploadPath == null) {
            // Use toAbsolutePath() so all resolved paths are absolute,
            // avoiding Tomcat temp-directory resolution issues with transferTo().
            uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        }
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                throw new RuntimeException("Could not create upload dir", e);
            }
        }
    }

    @Override
    public String store(MultipartFile file, String fileName) {
        try {
            if (fileName.startsWith("media/") || fileName.startsWith("media\\")) {
                fileName = fileName.substring("media/".length());
            }

            Path destinationFile = uploadPath.resolve(fileName).normalize();

            if (!destinationFile.startsWith(uploadPath)) {
                throw new RuntimeException("Cannot store file outside upload directory: " + fileName);
            }

            Path destinationDir = destinationFile.getParent();
            if (!Files.exists(destinationDir)) {
                Files.createDirectories(destinationDir);
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            if (!Files.exists(destinationFile)) {
                throw new RuntimeException("File was not created: " + destinationFile);
            }

            log.info("File stored successfully: {}", destinationFile);
            return "/media/" + fileName;

        } catch (IOException e) {
            log.error("Failed to store file: {}", fileName, e);
            throw new RuntimeException("Failed to store file: " + fileName, e);
        }
    }
}