package fr.paillaugue.school.r505.R505.vue;

import fr.paillaugue.school.r505.R505.controller.User;
import fr.paillaugue.school.r505.R505.controller.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/add")
  public @ResponseBody Integer addNewUser(@RequestBody User user) {
    Integer id = userService.createUser(user);
    return id;
  }

  @GetMapping("/{id}/get")
  public @ResponseBody User getUserById(@PathVariable Integer id) {
    User user = userService.findById(id);
    if (user == null) {
      return null;
    }
    return user;
  }

  @GetMapping("/all")
  public @ResponseBody Collection<User> getAllUsers() {
    Collection<User> users = userService.getAllUsers();
    return users;
  }
}
