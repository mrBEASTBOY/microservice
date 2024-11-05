package com.example.rest.webservices.monolith.controller;

import com.example.rest.webservices.monolith.entity.User;
import com.example.rest.webservices.monolith.exception.UserNotFoundException;
import com.example.rest.webservices.monolith.repository.UserRepository;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
  private UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping(path = "/users")
  public List<User> retrieveAllUsers() {
    return userRepository.findAll();
  }

  // Using Hateoas
  // EntityModel
  // WebMvcLinkBuilder
  @GetMapping(path = "/users/{id}")
  public EntityModel<User> retrieveUser(@PathVariable int id) {
    User user = userRepository.findUser(id);
    if (user == null) {
      throw new UserNotFoundException("id: " + id);
    }

    EntityModel<User> entityModel = EntityModel.of(user);

    WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(
      WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers()
    );

    entityModel.add(link.withRel("all-users"));

    return entityModel;
  }

  @PostMapping(path = "/users")
  public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
    User newUser = userRepository.addUser(user);
    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(newUser.getId())
      .toUri();
    return ResponseEntity.created(location).build();
  }

  @DeleteMapping(path = "/users/{id}")
  public void deleteUser(@PathVariable int id) {
    userRepository.deleteUserById(id);
  }
}
