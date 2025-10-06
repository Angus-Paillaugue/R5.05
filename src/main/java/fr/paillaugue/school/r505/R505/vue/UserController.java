package fr.paillaugue.school.r505.R505.vue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.paillaugue.school.r505.R505.modele.Roles;
import fr.paillaugue.school.r505.R505.modele.UserData;
import fr.paillaugue.school.r505.R505.modele.UserRepository;


@Controller
@RequestMapping(path = "/user")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @PostMapping(path = "/add")
  public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
    UserData n = new UserData();
    n.setName(name);
    n.setEmail(email);
    if(name.equals("admin"))
      n.setRole(Roles.moderator);
    else
      n.setRole(Roles.publisher);
    userRepository.save(n);
    return "Saved";
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<UserData> getAllUsers() {
    return userRepository.findAll();
  }

}
