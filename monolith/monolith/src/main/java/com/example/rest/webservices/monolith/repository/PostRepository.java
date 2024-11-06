package com.example.rest.webservices.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rest.webservices.monolith.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {}

