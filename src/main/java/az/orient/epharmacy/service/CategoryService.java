package az.orient.epharmacy.service;

import az.orient.epharmacy.dto.request.CategoryRequest;
import az.orient.epharmacy.dto.response.CategoryResponse;
import az.orient.epharmacy.dto.response.Response;
import az.orient.epharmacy.dto.response.StatusResponse;

import java.util.List;

public interface CategoryService {
    Response<List<CategoryResponse>> getCategoryList();

    Response<CategoryResponse> getCategoryById(Long categoryId);

    StatusResponse saveCategory(CategoryRequest categoryRequest);

    StatusResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);

    StatusResponse deleteCategory(Long categoryId);
}
