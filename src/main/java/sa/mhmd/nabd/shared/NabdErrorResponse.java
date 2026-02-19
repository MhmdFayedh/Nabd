package sa.mhmd.nabd.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@Setter
@Getter
public class NabdErrorResponse extends NabdResponse<Object> {
    private String errorCode;
}
