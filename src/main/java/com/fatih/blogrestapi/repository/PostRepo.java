package com.fatih.blogrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatih.blogrestapi.model.Post;

public interface PostRepo extends JpaRepository<Post, Long> {

    public List<Post> findByCategoryId(Long categoryId);

}