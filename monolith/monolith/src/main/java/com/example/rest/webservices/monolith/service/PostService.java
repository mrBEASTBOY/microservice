package com.example.rest.webservices.monolith.service;

import com.example.rest.webservices.monolith.entity.Post;
import com.example.rest.webservices.monolith.entity.User;
import jakarta.validation.Valid;

public interface PostService {
  public Post createPostForUser(User user, @Valid Post post);
}
