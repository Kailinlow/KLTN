package com.kltn.product_service.component.category.mapper;

import com.kltn.product_service.component.category.Category;
import com.kltn.product_service.component.category.dto.request.CategoryRequest;
import com.kltn.product_service.component.category.dto.response.CategoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest request);
    CategoryResponse toCategoryResponse(Category category);
    List<CategoryResponse> categoriesToResponses(List<Category> categories);
}
