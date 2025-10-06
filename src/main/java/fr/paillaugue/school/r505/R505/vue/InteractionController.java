package fr.paillaugue.school.r505.R505.vue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.paillaugue.school.r505.R505.controller.InteractionService;
import fr.paillaugue.school.r505.R505.modele.ArticleData;
import fr.paillaugue.school.r505.R505.modele.ArticleRepository;
import fr.paillaugue.school.r505.R505.modele.InteractionData;
import fr.paillaugue.school.r505.R505.modele.InteractionRepository;
import fr.paillaugue.school.r505.R505.modele.UserData;
import fr.paillaugue.school.r505.R505.modele.UserRepository;

@Controller
@RequestMapping(path = "/interaction")
public class InteractionController {
  @Autowired
  private InteractionRepository interactionRepository;
  @Autowired
  private InteractionService interactionService;
  @Autowired
  private ArticleRepository articleRepository;
  @Autowired
  private UserRepository userRepository;

  @PostMapping(path = "/add")
  public @ResponseBody String react(@RequestParam Integer articleId, @RequestParam Integer userId,
      @RequestParam String interaction) {
    ArticleData article = articleRepository.findById(articleId).orElse(null);
    if (article == null) {
      throw new IllegalArgumentException("The article ID you provided does not exist");
    }

    UserData user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      throw new IllegalArgumentException("The user ID you provided does not exist");
    }
    interactionService.react(article, user, interaction);
    return "Interaction saved";
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<InteractionData> getAllReactionsToArticle(@RequestParam Integer articleId) {
    ArticleData article = articleRepository.findById(articleId).orElse(null);
    if (article == null) {
      throw new IllegalArgumentException("The article ID you provided does not exist");
    }

    return interactionRepository.findAllByArticle(article);
  }
}
