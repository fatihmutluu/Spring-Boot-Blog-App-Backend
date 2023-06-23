package com.fatih.blogrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    public Long id;
    public String content;
    public String author;
    public String email;
}
