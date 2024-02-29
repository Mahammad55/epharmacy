package az.orient.epharmacy.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "kinds")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Kind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @OneToMany(mappedBy = "kind", fetch = FetchType.LAZY)
    Set<Medicine> medicines;

    @Builder.Default
    Integer active=1;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
}
