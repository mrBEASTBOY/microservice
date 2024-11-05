package com.example.rest.webservices.monolith.repository;

import com.example.rest.webservices.monolith.entity.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {
  private static List<User> users = new ArrayList<>();

  static {
    users =
      new ArrayList<>(
        Arrays.asList(
          new User(1, "Adam", LocalDate.now().minusYears(30)),
          new User(2, "Eve", LocalDate.now().minusYears(20)),
          new User(3, "Jim", LocalDate.now().minusYears(35)),
          new User(4, "Albert", LocalDate.now().minusYears(40))
        )
      );
  }

  public List<User> findAll() {
    return users;
  }

  public User findUser(int id) {
    return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
  }

  public User addUser(User user) {
    user.setId(users.size() + 1);
    users.add(user);
    return user;
  }
  
  public void deleteUserById(int id) {
	 users.removeIf(user -> user.getId() == id);
  }
}
