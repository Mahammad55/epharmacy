package az.orient.epharmacy.service.impl;

import az.orient.epharmacy.dto.request.CategoryRequest;
import az.orient.epharmacy.dto.response.CategoryResponse;
import az.orient.epharmacy.dto.response.Response;
import az.orient.epharmacy.dto.response.StatusResponse;
import az.orient.epharmacy.entity.Category;
import az.orient.epharmacy.enums.ActiveType;
import az.orient.epharmacy.exception.Message;
import az.orient.epharmacy.exception.PharmacyException;
import az.orient.epharmacy.mapper.CategoryMapper;
import az.orient.epharmacy.repository.CategoryRepository;
import az.orient.epharmacy.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;

    CategoryMapper mapper;

    @Override
    public Response<List<CategoryResponse>> getCategoryList() {
        Response<List<CategoryResponse>> response = new Response<>();
        List<Category> categoryList = categoryRepository.findAllByActive(ActiveType.ACTIVE.value);
        if (categoryList.isEmpty()) {
            throw new PharmacyException(HttpStatus.NOT_FOUND, Message.CATEGORY_NOT_FOUND);
        }

        List<CategoryResponse> categoryResponseList = categoryList.stream().map(mapper::entityToResponse).toList();
        response.setT(categoryResponseList);
        response.setStatus(StatusResponse.successMessage());

        return response;
    }

    @Override
    public Response<CategoryResponse> getCategoryById(Long categoryId) {
        Response<CategoryResponse> response = new Response<>();
        if (categoryId == null) {
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        Category category = categoryRepository.findCategoryByIdAndActive(categoryId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.CATEGORY_NOT_FOUND));

        CategoryResponse categoryResponse = mapper.entityToResponse(category);
        response.setT(categoryResponse);
        response.setStatus(StatusResponse.successMessage());

        return response;
    }

    @Override
    public StatusResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = mapper.requestToEntity(categoryRequest);
        categoryRepository.save(category);

        return StatusResponse.createdMessage();
    }

    @Override
    public StatusResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        if (categoryId == null) {
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        Category category = categoryRepository.findCategoryByIdAndActive(categoryId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.CATEGORY_NOT_FOUND));

        category.setName(categoryRequest.getName());
        categoryRepository.save(category);

        return StatusResponse.successMessage();
    }

    @Override
    public StatusResponse deleteCategory(Long categoryId) {
        if (categoryId == null) {
            throw new PharmacyException(HttpStatus.BAD_REQUEST, Message.INVALID_REQUEST_DATA);
        }

        Category category = categoryRepository.findCategoryByIdAndActive(categoryId, ActiveType.ACTIVE.value)
                .orElseThrow(() -> new PharmacyException(HttpStatus.NOT_FOUND, Message.CATEGORY_NOT_FOUND));

        category.setActive(ActiveType.DEACTIVE.value);
        categoryRepository.save(category);

        return StatusResponse.successMessage();
    }
}
