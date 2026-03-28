package com.wassim.blog.domain.mappers;

import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.wassim.blog.domain.Enums.PostStatus;
import com.wassim.blog.domain.dto.TagResponse;
import com.wassim.blog.domain.entities.Post;
import com.wassim.blog.domain.entities.Tag;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(target = "postsCount", source = "posts", qualifiedByName = "countPosts")
    TagResponse toTagResponse(Tag tag);

    @Named("countPosts")
    default Integer countPosts(Set<Post> posts) {
        if (posts == null) {
            return 0;
        }

        return (int) posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();
    }
}
