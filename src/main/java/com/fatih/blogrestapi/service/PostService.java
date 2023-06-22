package com.fatih.blogrestapi.service;

import java.util.List;
import com.fatih.blogrestapi.dto.PostDto;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

    PostDto updatePost(Long id, PostDto postDto);

    void deletePost(Long id);
}
