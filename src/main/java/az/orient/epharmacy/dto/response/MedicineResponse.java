package az.orient.epharmacy.dto.response;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicineResponse {
    Long id;

    String name;

    BigDecimal price;

    Integer count;

    String strength;

    String description;

    ManufacturerResponse manufacturerResponse;

    CategoryResponse categoryResponse;

    KindResponse kindResponse;

    Date fabDate;

    Date expDate;
}
