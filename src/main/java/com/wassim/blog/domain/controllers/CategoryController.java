package com.wassim.blog.domain.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wassim.blog.domain.dto.CategoryDto;
import com.wassim.blog.domain.mappers.CategoryMapper;
import com.wassim.blog.domain.services.intefaces.ICategoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(@RequestParam String param) {
        List<CategoryDto> categories = categoryService.getAllCategories().stream()
                .map(categoryMapper::toDto)
                .toList();
        return ResponseEntity.ok(categories);
    }

}
