package az.orient.epharmacy.repository;

import az.orient.epharmacy.entity.Kind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KindRepository extends JpaRepository<Kind, Long> {
    List<Kind> findAllByActive(Integer active);

    Optional<Kind> findKindByIdAndActive(Long id, Integer active);
}
