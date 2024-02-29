package az.orient.epharmacy.mapper;

import az.orient.epharmacy.dto.request.CategoryRequest;
import az.orient.epharmacy.dto.response.CategoryResponse;
import az.orient.epharmacy.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse entityToResponse(Category category);

    Category requestToEntity(CategoryRequest categoryRequest);
}