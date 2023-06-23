package com.fatih.blogrestapi.service;

import java.util.List;

import com.fatih.blogrestapi.dto.CommentDto;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getAllCommentsByPostId(long postId);

    CommentDto getCommentByPostIdAndCommentId(long postId, long commentId);

    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);

    void deleteComment(long postId, long commentId);
}
