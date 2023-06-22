package com.fatih.blogrestapi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    public List<PostDto> posts;
    public int totalPages;
    public int currentPage;
    public int pageSize;
    public long totalElements;
    public boolean hasNext;
    public boolean hasPrevious;
    
}
