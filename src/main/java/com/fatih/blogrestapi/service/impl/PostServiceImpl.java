package com.fatih.blogrestapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fatih.blogrestapi.dto.PostDto;
import com.fatih.blogrestapi.dto.PostResponse;
import com.fatih.blogrestapi.exception.BlogAPIException;
import com.fatih.blogrestapi.exception.ResourceNotFoundException;
import com.fatih.blogrestapi.model.Category;
import com.fatih.blogrestapi.model.Post;
import com.fatih.blogrestapi.repository.CategoryRepo;
import com.fatih.blogrestapi.repository.PostRepo;
import com.fatih.blogrestapi.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepo postRepository;
    private CategoryRepo categoryRepository;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepo postRepository, ModelMapper mapper, CategoryRepo categoryRepository) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    // ? create post
    @Override
    public PostDto createPost(PostDto postDto) {

        // find category by id
        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        // convert DTO to entity
        Post post = mapToEntity(postDto);
        post.setCategory(category);
        Post newPost = postRepository.save(post);

        // convert entity to DTO
        PostDto postResponse = mapToDTO(newPost);
        return postResponse;
    }

    // ? get all posts
    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        // sorting algorithm
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // get page objects
        Page<Post> posts = postRepository.findAll(pageable);

        // get content for page object
        List<Post> listOfPosts = posts.getContent();

        // convert entity to DTO
        List<PostDto> content = listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        // create PostResponse object
        PostResponse postResponse = new PostResponse(content, posts.getTotalPages(), posts.getNumber(), posts.getSize(),
                posts.getTotalElements(), posts.isLast());

        return postResponse;
    }

    // ? get post by id
    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    // ? update post
    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        // find category by id
        Category category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));

        // update post
        if (postDto.getTitle() != null) {
            if (postDto.getTitle().length() >= 2 && postDto.getTitle().length() <= 50)
                post.setTitle(postDto.getTitle());
            else
                throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Title cannot be null or less than 2 characters");
        }

        if (postDto.getContent() != null) {
            if (postDto.getContent().length() >= 10 && postDto.getContent().length() <= 400)
                post.setContent(postDto.getContent());
            else
                throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Content cannot be null or less than 10 characters");
        }
        if (postDto.getCategoryId() != null)
            post.setCategory(category);

        // save post and return updated post
        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    // ? delete post
    @Override
    public void deletePostById(long id) {
        // get post by id from the database and delete it
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    // ? get posts by category id
    @Override
    public List<PostDto> getPostsByCategoryId(Long categoryId) {
        // find category by id
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        // get posts by category
        List<Post> posts = postRepository.findByCategoryId(category.getId());

        // convert entity to DTO
        List<PostDto> content = posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        return content;
    }

    // ? search posts by title or content
    @Override
    public List<PostDto> searchPosts(String query) {

        List<Post> posts = postRepository.searchPosts(query);

        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    // *mappers
    // convert Entity into DTO
    private PostDto mapToDTO(Post post) {
        PostDto postDto = mapper.map(post, PostDto.class);
        return postDto;
    }

    // convert DTO to entity
    private Post mapToEntity(PostDto postDto) {
        Post post = mapper.map(postDto, Post.class);
        return post;
    }
}
