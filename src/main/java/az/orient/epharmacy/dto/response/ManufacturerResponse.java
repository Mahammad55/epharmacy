package az.orient.epharmacy.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManufacturerResponse {
    Long id;

    String name;
}
