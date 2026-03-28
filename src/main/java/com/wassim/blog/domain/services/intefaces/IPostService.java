package com.wassim.blog.domain.services.intefaces;

import java.util.List;
import java.util.UUID;

import com.wassim.blog.domain.entities.Post;

public interface IPostService {

    List<Post> getAllPosts(
            UUID categoryId,
            UUID tagId);

}
