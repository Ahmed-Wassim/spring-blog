package com.wassim.blog.domain.services.intefaces;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.wassim.blog.domain.entities.Tag;

public interface ITagService {
    List<Tag> getAllTags();

    List<Tag> createTags(Set<String> tagNames);

    void deleteTag(UUID id);

    public Tag getTagById(UUID id);

}
