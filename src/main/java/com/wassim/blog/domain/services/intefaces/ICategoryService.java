package com.wassim.blog.domain.services.intefaces;

import java.util.List;

import com.wassim.blog.domain.entities.Category;

public interface ICategoryService {

    List<Category> getAllCategories();
}
