package fr.paillaugue.school.r505.R505.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.paillaugue.school.r505.R505.modele.ArticleData;
import fr.paillaugue.school.r505.R505.modele.ArticleRepository;
import fr.paillaugue.school.r505.R505.modele.ReactionData;
import fr.paillaugue.school.r505.R505.modele.ReactionEnum;
import fr.paillaugue.school.r505.R505.modele.ReactionRepository;
import fr.paillaugue.school.r505.R505.modele.UserData;
import fr.paillaugue.school.r505.R505.modele.UserRepository;

@Service
public class ReactionService {
  @Autowired
  private ReactionRepository repository;
  @Autowired
  private ArticleRepository articleRepository;
  @Autowired
  private UserRepository userRepository;

  public void react(Integer articleId, Integer userId, String interactionStr) {
    ReactionEnum interaction;
    try {
      interaction = ReactionEnum.valueOf(interactionStr.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid interaction type: " + interactionStr);
    }

    ArticleData article = articleRepository.findById(articleId)
        .orElseThrow(() -> new IllegalArgumentException("Article not found"));

    UserData user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

    Optional<ReactionData> existingInteractionOpt = repository.findByArticleIdAndUserId(articleId, userId);

    if (existingInteractionOpt.isPresent()) {
      ReactionData existingInteraction = existingInteractionOpt.get();
      existingInteraction.setReaction(interaction);
      repository.save(existingInteraction);
    } else {
      ReactionData newInteraction = new ReactionData();
      newInteraction.setArticle(article);
      newInteraction.setUser(user);
      newInteraction.setReaction(interaction);
      repository.save(newInteraction);
    }
  }
}
