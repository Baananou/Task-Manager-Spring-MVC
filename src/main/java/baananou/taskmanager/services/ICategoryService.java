package baananou.taskmanager.services;

import baananou.taskmanager.models.Category;

import java.util.List;

public interface ICategoryService {

    Category getCategoryById(Long categoryId);

    List<Category> getAllCategories();

    Category createCategory(Category category);

    Category updateCategory(Long categoryId, Category category);

    void deleteCategory(Long categoryId);

}
