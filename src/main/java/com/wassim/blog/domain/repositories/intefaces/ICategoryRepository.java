package com.wassim.blog.domain.repositories.intefaces;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wassim.blog.domain.entities.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, UUID> {

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.posts p GROUP BY c")
    List<Category> findAllWithPostCount();
}
