package fr.paillaugue.school.r505.R505.modele;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ReactionData {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "article_id", nullable = false)
  private ArticleData article;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserData user;

  private ReactionEnum Reaction;

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
    return Reaction;
  }

  public void setReaction(ReactionEnum Reaction) {
    this.Reaction = Reaction;
  }

}
