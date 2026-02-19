package sa.mhmd.nabd.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import sa.mhmd.nabd.shared.enums.Errors;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NabdResponse<T> {
    private Boolean success;
    private String englishMessage;
    private String arabicMessage;
    private T data;

    public static <T> NabdResponse<T> success(T data) {
        return NabdResponse.<T>builder()
                .success(true)
                .englishMessage("Success Call")
                .arabicMessage("تمت العملية بنجاح")
                .data(data)
                .build();
    }

    public static NabdErrorResponse failure(String errorCodeString) {
        Errors error = Errors.fromCode(errorCodeString);
        return NabdErrorResponse.builder()
                .success(false)
                .errorCode(error.getCode())
                .englishMessage(error.getEnglishMessage())
                .arabicMessage(error.getArabicMessage())
                .build();
    }
}
