package com.wassim.blog.domain.services.classes;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.wassim.blog.domain.entities.Category;
import com.wassim.blog.domain.repositories.intefaces.ICategoryRepository;
import com.wassim.blog.domain.services.intefaces.ICategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("Category with name '" + category.getName() + "' already exists.");
        }
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteCategory(UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category with id '" + id + "' does not exist.");
        }
        categoryRepository.deleteById(id);
    }

    public Category getCategoryById(UUID id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category with id '" + id + "' does not exist."));
        return categoryRepository.findById(id).get();
    }

}
