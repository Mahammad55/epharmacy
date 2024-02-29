package az.orient.epharmacy.repository;

import az.orient.epharmacy.entity.Category;
import az.orient.epharmacy.entity.Kind;
import az.orient.epharmacy.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findAllByActive(Integer active);

    Optional<Medicine> findMedicineByNameAndActive(String name, Integer active);

    Optional<Medicine> findMedicineByIdAndActive(Long id, Integer active);

    List<Medicine> findAllByCategoryAndActive(Category category, Integer active);

    List<Medicine> findAllByKindAndActive(Kind kind, Integer active);
}
