package com.wassim.blog.domain.services.classes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wassim.blog.domain.entities.Category;
import com.wassim.blog.domain.repositories.intefaces.ICategoryRepository;
import com.wassim.blog.domain.services.intefaces.ICategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAllWithPostCount();
    }
}
