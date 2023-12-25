package baananou.taskmanager.services;

import baananou.taskmanager.models.Category;
import baananou.taskmanager.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        return categoryOptional.orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        validateCategoryBeforeCreate(category);
        return categoryRepository.save(category);
    }

    private void validateCategoryBeforeCreate(Category category) {
        // Validate name
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name is required");
        }
    }

    @Override
    public Category updateCategory(Long categoryId, Category updatedCategory) {
        Optional<Category> existingCategoryOptional = categoryRepository.findById(categoryId);
        if (existingCategoryOptional.isPresent()) {
            Category existingCategory = existingCategoryOptional.get();
            existingCategory.setName(updatedCategory.getName());
            return categoryRepository.save(existingCategory);
        } else {
            throw new IllegalArgumentException("Category with ID " + categoryId + " not found");
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
