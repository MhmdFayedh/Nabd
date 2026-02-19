package sa.mhmd.nabd.modules.cms;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@ConditionalOnProperty(name = "app.storage.type", havingValue = "s3")
@RequiredArgsConstructor
@Slf4j
public class S3MediaStorageService implements MediaStorageService {
    private final AmazonS3 amazonS3;

    @Override
    public String store(MultipartFile file, String fileName) {
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest("your-bucket", fileName,
                    file.getInputStream(), new ObjectMetadata());

            PutObjectResult result = amazonS3.putObject(putObjectRequest);
            log.info("File uploaded successfully. ETag: {}", result.getETag());

            return amazonS3.getUrl("your-bucket", fileName).toString();

        } catch (AmazonS3Exception s3Exception) {
            log.error("S3 error uploading file {}: HTTP {} - {}", fileName,
                    s3Exception.getStatusCode(), s3Exception.getErrorMessage());
            throw new RuntimeException("S3 upload failed: " + s3Exception.getErrorMessage(), s3Exception);

        } catch (IOException ioException) {
            log.error("IO error reading file input stream for {}", fileName, ioException);
            throw new RuntimeException("Failed to read file input stream", ioException);

        } catch (Exception e) {
            log.error("Unexpected error uploading file {} to S3", fileName, e);
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }
}
