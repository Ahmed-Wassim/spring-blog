package com.wassim.blog.domain.services.intefaces;

import java.util.List;
import java.util.UUID;

import com.wassim.blog.domain.entities.Category;

public interface ICategoryService {

    List<Category> getAllCategories();

    Category createCategory(Category category);

    void deleteCategory(UUID id);

    public Category getCategoryById(UUID id);
}
