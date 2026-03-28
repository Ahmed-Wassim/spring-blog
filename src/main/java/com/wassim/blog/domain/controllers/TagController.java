package com.wassim.blog.domain.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wassim.blog.domain.dto.CreateTagRequest;
import com.wassim.blog.domain.dto.TagResponse;
import com.wassim.blog.domain.entities.Tag;
import com.wassim.blog.domain.mappers.TagMapper;
import com.wassim.blog.domain.services.intefaces.ITagService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final ITagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<Tag> tags = tagService.getAllTags();
        List<TagResponse> tagResponses = tags.stream().map(tag -> tagMapper.toTagResponse(tag)).toList();
        return ResponseEntity.ok(tagResponses);
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTag(@Valid @RequestBody CreateTagRequest request) {
        List<Tag> savedTags = tagService.createTags(request.getNames());
        List<TagResponse> tagResponses = savedTags.stream().map(tag -> tagMapper.toTagResponse(tag)).toList();
        return ResponseEntity.status(HttpStatus.CREATED).body(tagResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@org.springframework.web.bind.annotation.PathVariable java.util.UUID id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
