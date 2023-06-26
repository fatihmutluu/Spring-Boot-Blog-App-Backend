package com.fatih.blogrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fatih.blogrestapi.model.Post;

public interface PostRepo extends JpaRepository<Post, Long> {

    public List<Post> findByCategoryId(Long categoryId);

    @Query("SELECT p FROM Post p WHERE "
            + "p.title LIKE CONCAT('%', :query, '%')"
            + "Or p.content LIKE CONCAT ('%', :query, '%')")
    public List<Post> searchPosts(String query);

}