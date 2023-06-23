package com.fatih.blogrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatih.blogrestapi.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long postId);

}
