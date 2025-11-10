package fr.paillaugue.school.r505.R505.vue;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import fr.paillaugue.school.r505.R505.controller.Article;
import fr.paillaugue.school.r505.R505.controller.ArticleService;

@Controller
@RequestMapping(path = "/article")
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @PostMapping("/add")
  public ResponseEntity<Integer> addNewArticle(@RequestBody Map<String, Object> request) {
    String contenu = (String) request.get("contenu");
    Integer userId = (Integer) request.get("userId");
    Integer articleId = articleService.createArticle(contenu, userId);
    return ResponseEntity.ok(articleId);
  }

  @GetMapping("/{id}/get")
  public ResponseEntity<Article> getArticle(@PathVariable Integer id) {
    Article article = articleService.findById(id);
    return ResponseEntity.ok(article);
  }

  @GetMapping(path = "/all")
  public ResponseEntity<Collection<Article>> getAllArticles() {
    Collection<Article> articles = articleService.getAll();
    return ResponseEntity.ok(articles);
  }
}
