package com.fatih.blogrestapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CommentDto {
    public Long id;

    @NotEmpty
    @Size(min = 2, max = 100, message = "Content must be between 2 and 100 characters")
    public String content;

    @NotEmpty
    @Size(min = 2, max = 30, message = "Author must be between 2 and 30 characters")
    public String author;

    @NotEmpty
    @Email
    public String email;
}
