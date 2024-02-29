package az.orient.epharmacy.mapper;

import az.orient.epharmacy.dto.request.KindRequest;
import az.orient.epharmacy.dto.response.KindResponse;
import az.orient.epharmacy.entity.Kind;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KindMapper {
    KindResponse entityToResponse(Kind kind);

    Kind requestToEntity(KindRequest kindRequest);
}
