package sa.mhmd.nabd.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NabdBusinessException extends RuntimeException {
    private final String errorCode;

    public NabdBusinessException(String errorCode) {
        this.errorCode = errorCode;
    }
}
