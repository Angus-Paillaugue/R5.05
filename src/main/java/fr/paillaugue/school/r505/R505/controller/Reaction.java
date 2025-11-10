package fr.paillaugue.school.r505.R505.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.paillaugue.school.r505.R505.modele.ArticleData;
import fr.paillaugue.school.r505.R505.modele.ReactionData;
import fr.paillaugue.school.r505.R505.modele.ReactionEnum;
import fr.paillaugue.school.r505.R505.modele.UserData;

public class Reaction {
  private Integer id;
  private UserData user;
  private ReactionEnum reaction;
  @JsonIgnore
  private ArticleData article;

  public Reaction(ReactionData reaction) {
    this.id = reaction.getId();
    this.article = reaction.getArticle();
    this.user = reaction.getUser();
    this.reaction = reaction.getReaction();
  }

  public Reaction() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ArticleData getArticle() {
    return article;
  }

  public void setArticle(ArticleData article) {
    this.article = article;
  }

  public UserData getUser() {
    return user;
  }

  public void setUser(UserData user) {
    this.user = user;
  }

  public ReactionEnum getReaction() {
    return reaction;
  }

  public void setReaction(ReactionEnum Reaction) {
    this.reaction = Reaction;
  }
}
