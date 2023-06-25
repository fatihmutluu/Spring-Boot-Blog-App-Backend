package com.fatih.blogrestapi.service;

import com.fatih.blogrestapi.dto.PostDto;
import com.fatih.blogrestapi.dto.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
