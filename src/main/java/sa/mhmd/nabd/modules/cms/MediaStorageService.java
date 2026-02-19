package sa.mhmd.nabd.modules.cms;

import org.springframework.web.multipart.MultipartFile;

interface MediaStorageService {
    String store(MultipartFile file, String fileName);
}
