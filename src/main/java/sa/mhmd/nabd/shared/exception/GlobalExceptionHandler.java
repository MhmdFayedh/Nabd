package sa.mhmd.nabd.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sa.mhmd.nabd.shared.NabdErrorResponse;
import sa.mhmd.nabd.shared.NabdResponse;
import sa.mhmd.nabd.shared.enums.Errors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NabdApplicationException.class)
    public ResponseEntity<NabdErrorResponse> handleNabdApplicationException(
            NabdApplicationException ex) {
        Errors error = Errors.fromCode(ex.getErrorCode());

        return ResponseEntity.status(error.getStatus()).body(NabdResponse.failure(ex.getErrorCode()));
    }

    @ExceptionHandler(NabdBusinessException.class)
    public ResponseEntity<?> handleNabdApplicationException(
            NabdBusinessException ex) {
        Errors error = Errors.fromCode(ex.getErrorCode());

        return ResponseEntity.status(error.getStatus()).body(NabdResponse.failure(ex.getErrorCode()));
    }
}
