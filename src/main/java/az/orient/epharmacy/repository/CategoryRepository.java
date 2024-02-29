package az.orient.epharmacy.repository;

import az.orient.epharmacy.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByActive(Integer active);

    Optional<Category> findCategoryByIdAndActive(Long id, Integer active);
}
