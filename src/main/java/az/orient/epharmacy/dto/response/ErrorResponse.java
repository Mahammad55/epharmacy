package az.orient.epharmacy.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {
    HttpStatus status;

    LocalDateTime timestamp;

    Integer code;

    String message;
}
