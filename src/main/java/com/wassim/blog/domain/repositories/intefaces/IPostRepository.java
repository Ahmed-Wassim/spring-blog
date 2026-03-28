package com.wassim.blog.domain.repositories.intefaces;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wassim.blog.domain.Enums.PostStatus;
import com.wassim.blog.domain.entities.Category;
import com.wassim.blog.domain.entities.Post;
import com.wassim.blog.domain.entities.Tag;

@Repository
public interface IPostRepository extends JpaRepository<Post, UUID> {

    List<Post> findAllByCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);

    List<Post> findAllByStatusAndCategory(PostStatus status, Category category);

    List<Post> findAllByStatusAndTags(PostStatus status, Tag tag);

    List<Post> findAllByStatus(PostStatus status);
}
