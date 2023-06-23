package com.fatih.blogrestapi.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {

    private long id;
    private String title;
    private String content;
    private Set<CommentDto> comments;

    public PostDto(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

}
