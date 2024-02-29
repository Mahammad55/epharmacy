package az.orient.epharmacy.mapper;

import az.orient.epharmacy.dto.request.ManufacturerRequest;
import az.orient.epharmacy.dto.response.ManufacturerResponse;
import az.orient.epharmacy.entity.Manufacturer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManufacturerMapper {
    ManufacturerResponse entityToResponse(Manufacturer manufacturer);

    Manufacturer requestToEntity(ManufacturerRequest manufacturerRequest);
}
