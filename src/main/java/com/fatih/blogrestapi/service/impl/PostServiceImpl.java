package com.fatih.blogrestapi.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fatih.blogrestapi.dto.CommentDto;
import com.fatih.blogrestapi.dto.PostDto;
import com.fatih.blogrestapi.dto.PostResponse;
import com.fatih.blogrestapi.exception.ResourceNotFoundError;
import com.fatih.blogrestapi.model.Comment;
import com.fatih.blogrestapi.model.Post;
import com.fatih.blogrestapi.repository.CommentRepo;
import com.fatih.blogrestapi.repository.PostRepo;
import com.fatih.blogrestapi.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    public PostRepo postRepo;
    public CommentRepo commentRepo;

    public PostServiceImpl(PostRepo postRepo, CommentRepo commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    // ?create post and save to database
    @Override
    public PostDto createPost(PostDto postDto) {

        // convert Dto to Entity
        // save Entity to database
        Post post = new Post(postDto.getId(), postDto.getTitle(), postDto.getContent());
        Post newPost = postRepo.save(post);

        // convert Entity to Dto
        PostDto newPostDto = new PostDto(newPost.getId(), newPost.getTitle(), newPost.getContent());
        return newPostDto;

    }

    // ?get all posts from database
    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // get all Entity from database
        // page the results
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> pages = postRepo.findAll(pageable);

        //
        List<Post> posts = pages.getContent();

        // convert Entity to Dto
        List<PostDto> postDtos = posts.stream()
                .map(post -> new PostDto(post.getId(), post.getTitle(), post.getContent()))
                .collect(Collectors.toList());

        // convert Dto to Response
        PostResponse postResponse = new PostResponse(postDtos, pages.getTotalPages(), pages.getNumber() + 1,
                pages.getSize(), pages.getTotalElements(), pages.hasNext(), pages.hasPrevious());

        // return Dto
        return postResponse;

    }

    // ?get post by id from database
    @Override
    public PostDto getPostById(Long id) {

        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundError("Post", "id", id));

        Set<CommentDto> comments = commentRepo.findByPostId(id).stream()
                .map(comment -> new CommentDto(comment.getId(), comment.getContent(), comment.getAuthor(),
                        comment.getEmail()))
                .collect(Collectors.toSet());

        PostDto postDto = new PostDto(post.getId(), post.getTitle(), post.getContent(), comments);
        return postDto;

    }

    // ?update post by id from database
    @Override
    public PostDto updatePost(Long id, PostDto postDto) {

        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundError("Post", "id", id));
        if (postDto.getTitle() != null)
            post.setTitle(postDto.getTitle());
        if (postDto.getContent() != null)
            post.setContent(postDto.getContent());
        Post updatedPost = postRepo.save(post);
        PostDto updatedPostDto = new PostDto(updatedPost.getId(), updatedPost.getTitle(), updatedPost.getContent());
        return updatedPostDto;

    }

    // ?delete post by id from database
    @Override
    public void deletePost(Long id) {

        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundError("Post", "id", id));
        postRepo.delete(post);

    }
}
