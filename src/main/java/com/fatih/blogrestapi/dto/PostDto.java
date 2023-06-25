package com.fatih.blogrestapi.dto;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {

    private long id;

    @NotEmpty
    @Size(min = 2, max = 50, message = "Title must be between 2-50 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, max = 400, message = "Content must be between 10-400 characters")
    private String content;

    private Set<CommentDto> comments;

}
