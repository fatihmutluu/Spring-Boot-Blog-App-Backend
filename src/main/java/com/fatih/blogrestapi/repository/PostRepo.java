package com.fatih.blogrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatih.blogrestapi.model.Post;

public interface PostRepo extends JpaRepository<Post, Long> {
}