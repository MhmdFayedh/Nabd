package sa.mhmd.nabd.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NabdApplicationException extends RuntimeException {
    private final String errorCode;

    public NabdApplicationException(String errorCode, HttpStatus httpStatus) {
        this.errorCode = errorCode;
    }

}
