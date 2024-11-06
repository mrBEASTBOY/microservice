package com.example.rest.webservices.monolith.service;

import com.example.rest.webservices.monolith.entity.User;
import java.util.List;

public interface UserService {
  public List<User> findAll();

  public User findUser(int id);

  public User addUser(User user);

  public void deleteUserById(int id);
}
