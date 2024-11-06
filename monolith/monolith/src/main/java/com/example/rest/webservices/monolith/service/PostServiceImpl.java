package com.example.rest.webservices.monolith.service;

import com.example.rest.webservices.monolith.entity.Post;
import com.example.rest.webservices.monolith.entity.User;
import com.example.rest.webservices.monolith.repository.PostRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
  private PostRepository postRepository;

  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public Post createPostForUser(User user, @Valid Post post) {
    post.setUser(user);
    postRepository.save(post);
    return post;
  }
}
