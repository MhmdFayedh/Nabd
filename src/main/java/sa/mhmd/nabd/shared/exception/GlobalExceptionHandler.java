package sa.mhmd.nabd.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sa.mhmd.nabd.shared.NabdErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NabdApplicationException.class)
    public ResponseEntity<NabdErrorResponse> handleNabdApplicationException(
            NabdApplicationException ex) {
        NabdErrorResponse errorResponse = NabdErrorResponse.failure(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
