package fr.paillaugue.school.r505.R505.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.paillaugue.school.r505.R505.modele.ArticleData;
import fr.paillaugue.school.r505.R505.modele.InteractionData;
import fr.paillaugue.school.r505.R505.modele.InteractionEnum;
import fr.paillaugue.school.r505.R505.modele.InteractionRepository;
import fr.paillaugue.school.r505.R505.modele.UserData;

@Service
public class InteractionService {
  @Autowired
  private InteractionRepository repository;

  public void react(ArticleData article, UserData user, String interactionStr) {
    InteractionEnum interaction = InteractionEnum.valueOf(interactionStr.toUpperCase());
    boolean hasAlreadyReacted = (article.getInteractions().stream().map((i) -> i.getUser().getId())).collect(Collectors.toSet()).contains(user.getId());
    if(hasAlreadyReacted) {
      Integer reactionId = article.getInteractions().stream().filter((i) -> i.getUser().getId() == user.getId()).collect(Collectors.toList()).get(0).getId();

      InteractionData i = repository.findById(reactionId).get();
      i.setInteraction(interaction);
      repository.save(i);
    }else {
      InteractionData newInteraction = new InteractionData();
      newInteraction.setArticle(article);
      newInteraction.setUser(user);
      newInteraction.setInteraction(interaction);

      repository.save(newInteraction);
    }
  }
}
