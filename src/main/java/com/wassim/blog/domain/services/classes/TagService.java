package com.wassim.blog.domain.services.classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wassim.blog.domain.entities.Tag;
import com.wassim.blog.domain.repositories.intefaces.ITagRepository;
import com.wassim.blog.domain.services.intefaces.ITagService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService implements ITagService {

    private final ITagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAllWithPostCount();
    }

    @Override
    public List<Tag> createTags(Set<String> tagNames) {
        List<Tag> existingTags = tagRepository.findByNameIn(tagNames);
        Set<String> existingTagNames = existingTags.stream().map(Tag::getName)
                .collect(Collectors.toSet());

        List<Tag> newTags = tagNames.stream()
                .filter(name -> !existingTagNames.contains(name))
                .map(name -> Tag.builder().name(name).posts(new HashSet<>()).build())
                .toList();

        List<Tag> savedTags = new ArrayList<>();
        if (!newTags.isEmpty()) {
            savedTags = tagRepository.saveAll(newTags);
        }

        return savedTags;
    }

    @Override
    @Transactional
    public void deleteTag(UUID id) {
        if (!tagRepository.existsById(id)) {
            throw new IllegalArgumentException("Tag with id '" + id + "' does not exist.");
        }
        tagRepository.deleteById(id);
    }

    public Tag getTagById(UUID id) {
        tagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tag with id '" + id + "' does not exist."));
        return tagRepository.findById(id).get();
    }

}
