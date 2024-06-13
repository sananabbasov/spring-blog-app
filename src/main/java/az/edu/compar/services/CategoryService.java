package az.edu.compar.services;

import az.edu.compar.dtos.CategoryDtos.CategoryCreateDto;
import az.edu.compar.dtos.CategoryDtos.CategoryDto;
import az.edu.compar.dtos.CategoryDtos.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {

    boolean createCategory(CategoryCreateDto categoryCreateDto);
    CategoryUpdateDto updateCategory(Long id, CategoryUpdateDto categoryUpdateDto);
    List<CategoryDto> getAllCategories();
}
