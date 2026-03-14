package com.wassim.blog.domain.repositories.intefaces;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wassim.blog.domain.entities.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, UUID> {

}
