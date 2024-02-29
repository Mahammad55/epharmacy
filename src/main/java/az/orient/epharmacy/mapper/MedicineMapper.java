package az.orient.epharmacy.mapper;

import az.orient.epharmacy.dto.request.MedicineRequest;
import az.orient.epharmacy.dto.response.MedicineResponse;
import az.orient.epharmacy.entity.Medicine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicineMapper {
    @Mapping(target = "manufacturerResponse", source = "manufacturer")
    @Mapping(target = "categoryResponse", source = "category")
    @Mapping(target = "kindResponse", source = "kind")
    MedicineResponse entityToResponse(Medicine medicine);

    @Mapping(target = "manufacturer.id", source = "manufacturerId")
    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "kind.id", source = "kindId")
    Medicine requestToEntity(MedicineRequest medicineRequest);
}
