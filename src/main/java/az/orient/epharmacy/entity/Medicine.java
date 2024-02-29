package az.orient.epharmacy.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "medicines")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    BigDecimal price;

    @Column(nullable = false)
    Integer count;

    @Column(nullable = false)
    String strength;

    @Column(nullable = false)
    String description;

    @ManyToOne(optional = false,targetEntity = Manufacturer.class)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    Manufacturer manufacturer;

    @ManyToOne(optional = false, targetEntity = Category.class)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    Category category;

    @ManyToOne(optional = false, targetEntity = Kind.class)
    @JoinColumn(name = "kind_id", referencedColumnName = "id")
    Kind kind;

    @Temporal(TemporalType.DATE)
    Date fabDate;

    @Temporal(TemporalType.DATE)
    Date expDate;

    @Builder.Default
    Integer active = 1;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
}
