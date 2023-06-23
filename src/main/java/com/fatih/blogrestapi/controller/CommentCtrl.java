package com.fatih.blogrestapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatih.blogrestapi.dto.CommentDto;
import com.fatih.blogrestapi.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentCtrl {

    private CommentService commentService;

    public CommentCtrl(CommentService commentService) {
        this.commentService = commentService;
    }

    // ! creating comment
    @PostMapping("create/{postId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @RequestBody CommentDto commentDto) {

        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);

    }

    // ! getting all comments by post id
    @GetMapping("{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable long postId) {

        return new ResponseEntity<>(commentService.getAllCommentsByPostId(postId), HttpStatus.OK);
    }

    // ! get comment by postid and commentid
    @GetMapping("{postId}/{commentId}")
    public ResponseEntity<CommentDto> getCommentByPostIdAndCommentId(
            @PathVariable long postId,
            @PathVariable long commentId) {

        return new ResponseEntity<>(commentService.getCommentByPostIdAndCommentId(postId, commentId), HttpStatus.OK);
    }

    // ! update comment
    @PutMapping("{postId}/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable long postId,
            @PathVariable long commentId,
            @RequestBody CommentDto commentDto) {

        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
    }

    // ! delete comment
    @DeleteMapping("{postId}/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable long postId,
            @PathVariable long commentId) {

        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted succesfully", HttpStatus.OK);
    }
}
