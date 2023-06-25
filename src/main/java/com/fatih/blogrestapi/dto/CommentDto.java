package com.fatih.blogrestapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100, message = "Content must be between 2 and 100 characters")
    private String content;

    @NotEmpty
    @Size(min = 2, max = 30, message = "Author must be between 2 and 30 characters")
    private String author;

    @NotEmpty
    @Email
    private String email;
}
