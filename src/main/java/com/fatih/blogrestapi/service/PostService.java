package com.fatih.blogrestapi.service;

import com.fatih.blogrestapi.dto.PostDto;
import com.fatih.blogrestapi.dto.PostResponse;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(Long id, PostDto postDto);

    void deletePost(Long id);
}
