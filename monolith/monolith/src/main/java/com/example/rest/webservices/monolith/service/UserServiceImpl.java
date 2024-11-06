package com.example.rest.webservices.monolith.service;

import com.example.rest.webservices.monolith.entity.User;
import com.example.rest.webservices.monolith.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public User findUser(int id) {
    return userRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional
  public User addUser(User user) {
    System.out.println(user);
    userRepository.save(user);
    return user;
  }

  @Override
  @Transactional
  public void deleteUserById(int id) {
    userRepository.deleteById(id);
  }
}
