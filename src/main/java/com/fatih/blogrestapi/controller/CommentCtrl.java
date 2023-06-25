package com.fatih.blogrestapi.controller;

import com.fatih.blogrestapi.dto.CommentDto;
import com.fatih.blogrestapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentCtrl {

    private CommentService commentService;

    public CommentCtrl(CommentService commentService) {
        this.commentService = commentService;
    }

    // !create comment
    @PostMapping("{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
            @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    // !get post comments
    @GetMapping("{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    // !get comment by id
    @GetMapping("{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long commentId) {
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    // !update comment
    @PutMapping("{postId}/comments/{id}/update")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long commentId,
            @RequestBody CommentDto commentDto) {
        CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    // !delete comment
    @DeleteMapping("{postId}/comments/{id}/delete")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
