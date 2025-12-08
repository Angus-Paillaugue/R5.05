package fr.paillaugue.school.r505.R505.vue;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import fr.paillaugue.school.r505.R505.controller.Article;
import fr.paillaugue.school.r505.R505.controller.ArticleService;
import fr.paillaugue.school.r505.R505.errorHandling.Body;
import fr.paillaugue.school.r505.R505.errorHandling.Response;

@Controller
@RequestMapping(path = "/article")
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  @PostMapping("/add")
  public ResponseEntity<Response<Integer>> addNewArticle(@RequestBody Map<String, Object> request) {
    try {
      String contenu = Body.parseBody(request, "contenu");
      Integer userId = Body.parseBody(request, "userId");
      Integer articleId = articleService.createArticle(contenu, userId);
      return Response.success(articleId);
    } catch (Exception e) {
      return Response.error(e);
    }
  }

  @GetMapping("/{id}/get")
  public ResponseEntity<Response<Article>> getArticle(@PathVariable Integer id) {
    try {
      Article article = articleService.findById(id);
      if (article == null) {
        throw new Exception("Article not found");
      }
      return Response.success(article);
    } catch (Exception e) {
      return Response.error(e);
    }
  }

  @GetMapping(path = "/all")
  public ResponseEntity<Response<Collection<Article>>> getAllArticles() {
    try {
      Collection<Article> articles = articleService.getAll();
      return Response.success(articles);
    } catch (Exception e) {
      return Response.error(e);
    }
  }

  @DeleteMapping("/{id}/delete")
  public ResponseEntity<Response<String>> deleteArticle(@PathVariable Integer id,
      @RequestBody Map<String, Object> request) {
    try {
      Integer userId = Body.parseBody(request, "userId");
      articleService.deleteById(id, userId);
      return Response.success("Article deleted");
    } catch (Exception e) {
      return Response.error(e);
    }
  }

  @PatchMapping("/{id}/update")
  public ResponseEntity<Response<String>> updateArticle(@PathVariable Integer id,
      @RequestBody Map<String, Object> request) {
    try {
      Integer userId = Body.parseBody(request, "userId");
      String newContent = Body.parseBody(request, "contenu");
      articleService.update(id, userId, newContent);
      return Response.success("Article updated");
    } catch (Exception e) {
      return Response.error(e);
    }
  }
}
