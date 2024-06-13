package az.edu.compar.services.impls;

import az.edu.compar.dtos.CategoryDtos.CategoryCreateDto;
import az.edu.compar.dtos.CategoryDtos.CategoryDto;
import az.edu.compar.dtos.CategoryDtos.CategoryUpdateDto;
import az.edu.compar.entities.Category;
import az.edu.compar.exceptions.ResourceNotFoundException;
import az.edu.compar.repositories.CategoryRepository;
import az.edu.compar.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public boolean createCategory(CategoryCreateDto categoryCreateDto) {
            Category category = modelMapper.map(categoryCreateDto,Category.class);
            categoryRepository.save(category);
            return true;
    }

    @Override
    public CategoryUpdateDto updateCategory(Long id, CategoryUpdateDto categoryUpdateDto) {

        Category findCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category","category id", id));
        findCategory.setName(categoryUpdateDto.getName());
        categoryRepository.save(findCategory);
        return categoryUpdateDto;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categories = categoryRepository.findAll()
                .stream().map(x->modelMapper.map(x, CategoryDto.class))
                .collect(Collectors.toList());
        return categories;
    }
}
