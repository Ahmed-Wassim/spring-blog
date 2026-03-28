package com.wassim.blog.domain.services.classes;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wassim.blog.domain.Enums.PostStatus;
import com.wassim.blog.domain.entities.Category;
import com.wassim.blog.domain.entities.Post;
import com.wassim.blog.domain.entities.Tag;
import com.wassim.blog.domain.repositories.intefaces.IPostRepository;
import com.wassim.blog.domain.services.intefaces.ICategoryService;
import com.wassim.blog.domain.services.intefaces.IPostService;
import com.wassim.blog.domain.services.intefaces.ITagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final IPostRepository postRepository;
    private final ICategoryService categoryService;
    private final ITagService tagRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts(
            UUID categoryId,
            UUID tagId) {
        if (categoryId != null && tagId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagRepository.getTagById(tagId);
            postRepository.findAllByCategoryAndTagsContaining(
                    PostStatus.PUBLISHED, category, tag);
        }

        if(categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategory(
                    PostStatus.PUBLISHED, category);
        }

        if(tagId != null) {
            Tag tag = tagRepository.getTagById(tagId);
            return postRepository.findAllByStatusAndTags(
                    PostStatus.PUBLISHED, tag);
        }

        return postRepository.findAllByStatus(PostStatus.PUBLISHED);

    }

}
