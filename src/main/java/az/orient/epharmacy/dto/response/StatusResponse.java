package az.orient.epharmacy.dto.response;

import az.orient.epharmacy.exception.Message;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusResponse {
    Integer code;

    String message;

    public static StatusResponse successMessage() {
        return new StatusResponse(HttpStatus.OK.value(), Message.SUCCESS);
    }

    public static StatusResponse createdMessage() {
        return new StatusResponse(HttpStatus.CREATED.value(), Message.CREATED);
    }
}
