package az.orient.epharmacy.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PharmacyException extends RuntimeException {
    HttpStatus status;

    public PharmacyException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
