package com.fatih.blogrestapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fatih.blogrestapi.dto.CommentDto;
import com.fatih.blogrestapi.exception.BlogApiError;
import com.fatih.blogrestapi.exception.ResourceNotFoundError;
import com.fatih.blogrestapi.model.Comment;
import com.fatih.blogrestapi.model.Post;
import com.fatih.blogrestapi.repository.CommentRepo;
import com.fatih.blogrestapi.repository.PostRepo;
import com.fatih.blogrestapi.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepo commentRepo;
    private PostRepo postRepo;

    public CommentServiceImpl(CommentRepo commentRepo, PostRepo postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    // ? create Comment and save to database
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundError("Post", "id", postId));

        // convert Dto to Entity
        Comment comment = new Comment(commentDto.getId(), commentDto.getContent(), commentDto.getAuthor(),
                commentDto.getEmail(), post);

        // save Entity to database
        commentRepo.save(comment);

        // convert Entity to Dto
        CommentDto newCommentDto = new CommentDto(comment.getId(), comment.getContent(),
                comment.getAuthor(), comment.getEmail());

        return newCommentDto;
    }

    // ? get all comments from database
    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {

        // get all comments by post id
        List<Comment> comments = commentRepo.findByPostId(postId);

        // convert Entity to Dto
        List<CommentDto> commentDtos = comments.stream()
                .map(comment -> new CommentDto(
                        comment.getId(), comment.getContent(), comment.getAuthor(), comment.getEmail()))
                .collect(Collectors.toList());

        return commentDtos;
    }

    // ? get comment by post id and comment id
    @Override
    public CommentDto getCommentByPostIdAndCommentId(long postId, long commentId) {

        // get post and comment from database
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundError("Post", "id", postId));
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundError("Comment", "id", commentId));

        // check if comment belongs to post
        if (!(comment.getPost().getId() == post.getId())) {
            throw new BlogApiError(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }

        // convert Entity to Dto
        CommentDto commentDto = new CommentDto(comment.getId(), comment.getContent(), comment.getAuthor(),
                comment.getEmail());
        return commentDto;
    }

    // ? update comment and save to database
    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {

        // get post and comment from database
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundError("Post", "id", postId));
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundError("Comment", "id", commentId));

        // check if comment belongs to post
        if (!(comment.getPost().getId() == post.getId())) {
            throw new BlogApiError(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }

        // change comment fields
        if (commentDto.getContent() != null) {
            comment.setContent(commentDto.getContent());
        }
        if (commentDto.getAuthor() != null) {
            comment.setAuthor(commentDto.getAuthor());
        }
        if (commentDto.getEmail() != null) {
            comment.setEmail(commentDto.getEmail());
        }

        // save Entity to database
        commentRepo.save(comment);

        // convert Entity to Dto
        CommentDto newCommentDto = new CommentDto(comment.getId(), comment.getContent(),
                comment.getAuthor(), comment.getEmail());

        return newCommentDto;
    }

    // ? delete comment from database
    @Override
    public void deleteComment(long postId, long commentId) {

        // get post and comment from database
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundError("Post", "id", postId));
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundError("Comment", "id", commentId));

        // check if comment belongs to post
        if (!(comment.getPost().getId() == post.getId())) {
            throw new BlogApiError(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }

        // delete comment from database
        commentRepo.delete(comment);
    }

}
