package fr.paillaugue.school.r505.R505.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.paillaugue.school.r505.R505.modele.Roles;
import fr.paillaugue.school.r505.R505.modele.UserData;
import fr.paillaugue.school.r505.R505.modele.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;

  public Integer createUser(User user) {
    boolean userExists = repository.findByName(user.getName()) != null;
    if (userExists) {
      throw new IllegalArgumentException("User already exists");
    }
    Roles role = user.getName().equals("admin") ? Roles.moderator : Roles.publisher;
    user.setRole(role);
    UserData insertedUser = repository.saveAndFlush(user.toData());
    return insertedUser.getId();
  }

  public User findById(Integer id) {
    UserData a = repository.findById(id).orElse(null);
    if (a == null) {
      return null;
    }
    User user = new User(
      a.getName(),
      a.getEmail(),
      a.getPasswordHash(),
      a.getRole()
    );

    return user;
  }

  public Collection<User> getAllUsers() {
    return repository.findAll().stream().map(User::new).toList();
  }
}
