package fr.paillaugue.school.r505.R505.vue;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.paillaugue.school.r505.R505.modele.ArticleData;
import fr.paillaugue.school.r505.R505.modele.ArticleRepository;
import fr.paillaugue.school.r505.R505.modele.UserData;
import fr.paillaugue.school.r505.R505.modele.UserRepository;


@Controller
@RequestMapping(path = "/article")
public class ArticleController {
  @Autowired
  private ArticleRepository articleRepository;
  @Autowired
  private UserRepository userRepository;

  @PostMapping(path = "/add")
  public @ResponseBody String addNewArticle(@RequestParam String contenu, @RequestParam String userId) {
    ArticleData a = new ArticleData();
    a.setDatePublication(new Date(System.currentTimeMillis()));
    a.setContenu(contenu);

    Integer id = Integer.valueOf(userId);
    UserData user = userRepository.findById(id).orElse(null);
    if (user == null) {
      throw new Error("The user ID you provided does not exists");
    }
    a.setAuteur(user);

    articleRepository.save(a);
    return "Saved";
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<ArticleData> getAllUsers() {
    return articleRepository.findAll();
  }

  @DeleteMapping(path = "/delete")
  public @ResponseBody String deleteArticle(@RequestParam int id) {
    articleRepository.deleteById(id);

    return "Deleted";
  }

  @PutMapping(path = "/update")
  public @ResponseBody String updateArticle(@RequestParam int id, @RequestParam String contenu) {
    ArticleData a = articleRepository.findById(id).orElse(null);
    if(a == null) {
      throw new Error("The article ID you provided does not exists");
    }
    a.setContenu(contenu);
    articleRepository.save(a);

    return "Updated";
  }

}
