package az.orient.epharmacy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicineRequest {
    @NotBlank(message = "Name can not be null")
    String name;

    @Positive(message = "Price should be positive number")
    BigDecimal price;

    @Positive(message = "Count should be positive number")
    Integer count;

    @NotBlank(message = "Strength can not be null")
    String strength;

    @NotBlank(message = "Description can not be null")
    String description;

    Long manufacturerId;

    Long categoryId;

    Long kindId;

    @Past(message = "Fab date should not be in the future")
    Date fabDate;

    Date expDate;
}
