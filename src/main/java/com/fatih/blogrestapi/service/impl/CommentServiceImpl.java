package com.fatih.blogrestapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fatih.blogrestapi.dto.CommentDto;
import com.fatih.blogrestapi.exception.BlogAPIException;
import com.fatih.blogrestapi.exception.ResourceNotFoundException;
import com.fatih.blogrestapi.model.Comment;
import com.fatih.blogrestapi.model.Post;
import com.fatih.blogrestapi.repository.CommentRepo;
import com.fatih.blogrestapi.repository.PostRepo;
import com.fatih.blogrestapi.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepo commentRepository;
    private PostRepo postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepo commentRepository, PostRepo postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    // ? create comment
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // set post to comment entity
        comment.setPost(post);

        // comment entity to DB
        Comment newComment = commentRepository.save(comment);

        return mapToDTO(newComment);
    }

    // ? get all comments by post id
    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        // retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        // convert list of comment entities to list of comment dto's and return
        List<CommentDto> commentDtos = comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
        return commentDtos;
    }

    // ? get comment by id
    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // check if comment belongs to post
        if (!(comment.getPost().getId() == post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToDTO(comment);
    }

    // ? update comment
    @Override
    public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // check if comment belongs to post
        if (!(comment.getPost().getId() == post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }

        if (commentRequest.getAuthor() != null) {
            if (commentRequest.getAuthor().length() >= 2 && commentRequest.getAuthor().length() <= 30)
                comment.setAuthor(commentRequest.getAuthor());
            else
                throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Author must be between 2 and 30 characters");
        }

        if (commentRequest.getContent() != null) {

            if (commentRequest.getContent().length() >= 2 && commentRequest.getContent().length() <= 100)
                comment.setContent(commentRequest.getContent());
            else
                throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Content must be between 2 and 100 characters");

        }

        if (commentRequest.getEmail() != null) {
            if (commentRequest.getEmail().length() >= 4)
                comment.setEmail(commentRequest.getEmail());
            else
                throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email cannot be empty");
        }

        Comment updatedComment = commentRepository.save(comment);
        return mapToDTO(updatedComment);
    }

    // ? delete comment
    @Override
    public void deleteComment(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        // check if comment belongs to post
        if (!(comment.getPost().getId() == post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }

        commentRepository.delete(comment);
    }

    // *mappers
    private CommentDto mapToDTO(Comment comment) {
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = mapper.map(commentDto, Comment.class);
        return comment;
    }
}
