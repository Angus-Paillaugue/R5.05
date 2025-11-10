package fr.paillaugue.school.r505.R505.vue;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping(path = "/reaction")
public class ReactionController {
  @Autowired
  private ReactionService reactionService;
  @Autowired
  private ArticleService articleService;

  @PostMapping(path = "/add")
  public @ResponseBody String react(@RequestBody Map<String, Object> request) {
    Integer articleId = (Integer) request.get("articleId");
    Integer userId = (Integer) request.get("userId");
    String reactionType = (String) request.get("reaction");

    reactionService.react(
        articleId,
        userId,
        reactionType);
    return "Reaction saved";
  }

  @GetMapping(path = "/{id}/get")
  public @ResponseBody List<Reaction> getAllReactionsToArticle(@PathVariable Integer articleId) {
    Article article = articleService.findById(articleId);
    if (article == null) {
      throw new IllegalArgumentException("The article ID you provided does not exist");
    }

    return article.getReactions();
  }
}
