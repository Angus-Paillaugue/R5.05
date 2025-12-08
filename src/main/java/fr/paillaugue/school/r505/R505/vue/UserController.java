package fr.paillaugue.school.r505.R505.vue;

import fr.paillaugue.school.r505.R505.controller.User;
import fr.paillaugue.school.r505.R505.controller.UserService;
import fr.paillaugue.school.r505.R505.errorHandling.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/add")
  public @ResponseBody ResponseEntity<Response<Integer>> addNewUser(@RequestBody User user) {
    try {
      Integer id = userService.createUser(user);
      return Response.success(id);
    } catch (Exception e) {
      return Response.error(e);
    }
  }

  @GetMapping("/{id}/get")
  public @ResponseBody ResponseEntity<Response<User>> getUserById(@PathVariable Integer id) {
    try {
      User user = userService.findById(id);
      return Response.success(user);
    } catch (Exception e) {
      return Response.error(e);
    }
  }

  @GetMapping("/all")
  public @ResponseBody ResponseEntity<Response<Collection<User>>> getAllUsers() {
    try {
      Collection<User> users = userService.getAllUsers();
      return Response.success(users);
    } catch (Exception e) {
      return Response.error(e);
    }
  }
}
