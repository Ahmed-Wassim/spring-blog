package com.wassim.blog.domain.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.wassim.blog.domain.Enums.PostStatus;
import com.wassim.blog.domain.dto.CategoryDto;
import com.wassim.blog.domain.dto.CreateCategoryRequest;
import com.wassim.blog.domain.entities.Category;
import com.wassim.blog.domain.entities.Post;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDTO(Category category);

    Category toEntity(CreateCategoryRequest request);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        if (posts == null) {
            return 0;
        }

        return posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();
    }
}
