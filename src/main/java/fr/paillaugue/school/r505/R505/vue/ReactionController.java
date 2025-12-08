package fr.paillaugue.school.r505.R505.vue;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.paillaugue.school.r505.R505.controller.Article;
import fr.paillaugue.school.r505.R505.controller.ArticleService;
import fr.paillaugue.school.r505.R505.controller.Reaction;
import fr.paillaugue.school.r505.R505.controller.ReactionService;
import fr.paillaugue.school.r505.R505.errorHandling.Body;
import fr.paillaugue.school.r505.R505.errorHandling.Response;

@Controller
@RequestMapping(path = "/reaction")
public class ReactionController {
  @Autowired
  private ReactionService reactionService;
  @Autowired
  private ArticleService articleService;

  @PostMapping(path = "/add")
  public @ResponseBody ResponseEntity<Response<String>> react(@RequestBody Map<String, Object> request) {
    try {
      Integer articleId = Body.parseBody(request, "articleId");
      Integer userId = Body.parseBody(request, "userId");
      String reactionType = Body.parseBody(request, "reaction");
      reactionService.react(articleId, userId, reactionType);
      return Response.success("Reaction saved");
    } catch (Exception e) {
      return Response.error(e);
    }
  }

  @GetMapping(path = "/{id}/get")
  public @ResponseBody ResponseEntity<Response<List<Reaction>>> getAllReactionsToArticle(@PathVariable Integer id) {
    try {
      Article article = articleService.findById(id);
      if (article == null) {
        return Response.error("Article not found");
      }
      return Response.success(article.getReactions());
    } catch (Exception e) {
      return Response.error(e);
    }
  }
}
