package fr.paillaugue.school.r505.R505.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.paillaugue.school.r505.R505.modele.Article;
import fr.paillaugue.school.r505.R505.modele.Interaction;
import fr.paillaugue.school.r505.R505.modele.InteractionEnum;
import fr.paillaugue.school.r505.R505.modele.User;
import fr.paillaugue.school.r505.R505.vue.ArticleRepository;
import fr.paillaugue.school.r505.R505.vue.InteractionRepository;
import fr.paillaugue.school.r505.R505.vue.UserRepository;

@Controller
@RequestMapping(path = "/interaction")
public class InteractionController {
  @Autowired
  private InteractionRepository interactionRepository;
  @Autowired
  private ArticleRepository articleRepository;
  @Autowired
  private UserRepository userRepository;

  @PostMapping(path = "/add")
  public @ResponseBody String react(@RequestParam Integer articleId, @RequestParam Integer userId,
      @RequestParam String interaction) {
    Article article = articleRepository.findById(articleId).orElse(null);
    if (article == null) {
      throw new IllegalArgumentException("The article ID you provided does not exist");
    }

    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      throw new IllegalArgumentException("The user ID you provided does not exist");
    }
    Interaction newInteraction = new Interaction();
    newInteraction.setArticle(article);
    newInteraction.setUser(user);
    newInteraction.setInteraction(InteractionEnum.valueOf(interaction.toUpperCase()));

    interactionRepository.save(newInteraction);
    return "Interaction saved";
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<Interaction> getAllReactionsToArticle(@RequestParam Integer articleId) {
    Article article = articleRepository.findById(articleId).orElse(null);
    if (article == null) {
      throw new IllegalArgumentException("The article ID you provided does not exist");
    }

    return interactionRepository.findAllByArticle(article);
  }
}
