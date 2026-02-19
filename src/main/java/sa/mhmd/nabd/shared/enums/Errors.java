package sa.mhmd.nabd.shared.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Errors {
    CONTENT_NOT_FOUND("CMS_001", "Content not found", "المحتوى غير موجود", HttpStatus.NOT_FOUND),
    CONTENT_SLUG_EXISTS("CMS_002", "Content slug already exists", "رابط المحتوى موجود مسبقاً", HttpStatus.BAD_REQUEST),
    CONTENT_TYPE_INVALID("CMS_003", "Invalid content type", "نوع المحتوى غير صحيح", HttpStatus.BAD_REQUEST),
    TAG_NOT_FOUND("TAG_001", "Tag not found", "الوسم غير موجود", HttpStatus.NOT_FOUND),
    TAG_TOO_LONG("TAG_002", "Tag name too long", "اسم الوسم طويل جداً", HttpStatus.BAD_REQUEST),
    LANGUAGE_NOT_SUPPORTED("LANG_001", "Language not supported", "اللغة غير مدعومة", HttpStatus.BAD_REQUEST),
    VALIDATION_ERROR("VAL_001", "Validation failed", "بيانات التحقق غير صحيحة", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("INT_001", "Internal server error", "خطأ داخلي في الخادم", HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_ERROR("DB_001", "Database operation failed", "فشل عملية قاعدة البيانات", HttpStatus.INTERNAL_SERVER_ERROR),
    MEDIA_UPLOAD_FAILED("MEDIA_001", "Error uploading media", "خطأ في رفع الملف الإعلامي", HttpStatus.BAD_REQUEST),
    FILE_EMPTY("FILE_002", "File is empty", "الملف فارغ", HttpStatus.BAD_REQUEST),
    PODCAST_AUDIO_ONLY("MEDIA_003", "Podcast must be audio file", "البودكاست يجب أن يكون ملف صوتي", HttpStatus.BAD_REQUEST),
    DOCUMENTARY_VIDEO_ONLY("MEDIA_004", "Documentary must be video file", "الوثائقي يجب أن يكون ملف فيديو", HttpStatus.BAD_REQUEST),
    FILE_TOO_LARGE("FILE_005", "File too large (max 500MB)", "الملف كبير جداً (الحد الأقصى 500 ميجابايت)", HttpStatus.PAYLOAD_TOO_LARGE);
    private final String code;
    private final String englishMessage;
    private final String arabicMessage;
    private final HttpStatus status;

    Errors(String code, String englishMessage, String arabicMessage, HttpStatus status) {
        this.code = code;
        this.englishMessage = englishMessage;
        this.arabicMessage = arabicMessage;
        this.status = status;
    }

    public static Errors fromCode(String code) {
        for (Errors error : Errors.values()) {
            if (error.code.equals(code)) {
                return error;
            }
        }
        return INTERNAL_SERVER_ERROR;
    }
}
