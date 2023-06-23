package com.fatih.blogrestapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatih.blogrestapi.dto.PostDto;
import com.fatih.blogrestapi.dto.PostResponse;
import com.fatih.blogrestapi.service.PostService;
import com.fatih.blogrestapi.utils.AppConstants;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/posts")
public class PostCtrl {

    private PostService postService;

    public PostCtrl(PostService postService) {
        this.postService = postService;
    }

    // !creating post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }

    // !getting all posts
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NO, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {

        return postService.getAllPosts(pageNo - 1, pageSize, sortBy, sortDir);

    }

    // !getting post by id
    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable Long id) {

        return postService.getPostById(id);

    }

    // !updating post by id
    @PutMapping(value = "{id}/update")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") Long id, @RequestBody PostDto postDto) {

        return new ResponseEntity<>(postService.updatePost(id, postDto), HttpStatus.OK);

    }

    // !deleting post by id
    @DeleteMapping(value = "{id}/delete")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {

        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);

    }
}
