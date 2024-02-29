package az.orient.epharmacy.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE )
public class Response<T> {
    @JsonProperty(value = "data")
    T t;

    StatusResponse status;
}
