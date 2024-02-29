package az.orient.epharmacy.repository;

import az.orient.epharmacy.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
    List<Manufacturer> findAllByActive(Integer active);

    Optional<Manufacturer> findManufacturerByIdAndActive(Long id, Integer active);
}
