package com.fatih.blogrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatih.blogrestapi.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}
