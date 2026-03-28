package com.wassim.blog.domain.dto;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTagRequest {
    @NotEmpty(message = "Tag names cannot be empty")
    @Size(max = 10, message = "A maximum of 10 tags can be created at once")
    private Set<@Size(min = 2, max = 30, message = "Tag name must be between 2 and 30 characters") @Pattern(regexp = "^[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*$", message = "Tag name must be alphanumeric and can include hyphens") String> names;
}
