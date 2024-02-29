package az.orient.epharmacy.controller;

import az.orient.epharmacy.dto.request.CategoryRequest;
import az.orient.epharmacy.dto.response.CategoryResponse;
import az.orient.epharmacy.dto.response.Response;
import az.orient.epharmacy.dto.response.StatusResponse;
import az.orient.epharmacy.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/categories")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class CategoryController {
    CategoryService categoryService;

    @GetMapping
    public Response<List<CategoryResponse>> getCategoryList() {
        return categoryService.getCategoryList();
    }

    @GetMapping("/{id}")
    public Response<CategoryResponse> getCategoryById(@PathVariable(value = "id", required = false) Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponse saveCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return categoryService.saveCategory(categoryRequest);
    }

    @PutMapping("/{id}")
    public StatusResponse updateCategory(
            @PathVariable(value = "id", required = false) Long categoryId,
            @RequestBody @Valid CategoryRequest categoryRequest) {
        return categoryService.updateCategory(categoryId, categoryRequest);
    }

    @DeleteMapping("/{id}")
    public StatusResponse deleteCategory(@PathVariable(value = "id", required = false) Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
