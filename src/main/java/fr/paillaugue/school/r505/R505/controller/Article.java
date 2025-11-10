package fr.paillaugue.school.r505.R505.controller;

import java.sql.Date;
import java.util.List;

import fr.paillaugue.school.r505.R505.modele.ArticleData;

public class Article {
  private Integer id;
  private String contenu;
  private Date publication;
  private User auteur;
  private List<Reaction> reactions;

  public Article(ArticleData a) {
    this.id = a.getId();
    this.contenu = a.getContenu();
    this.publication = a.getDatePublication();
    this.auteur = (a.getAuteur() != null) ? new User(a.getAuteur()) : null; // Handle null auteur
    this.reactions = a.getReactions() != null
        ? a.getReactions().stream().map(Reaction::new).toList()
        : List.of(); // Handle null reactions
  }

  public Article() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getContenu() {
    return contenu;
  }

  public void setContenu(String contenu) {
    this.contenu = contenu;
  }

  public Date getDatePublication() {
    return publication;
  }

  public void setDatePublication(Date publication) {
    this.publication = publication;
  }

  public User getAuteur() {
    return this.auteur;
  }

  public void setAuteur(User user) {
    this.auteur = user;
  }

  public List<Reaction> getReactions() {
    return reactions;
  }

  public void setReactions(List<Reaction> reactions) {
    this.reactions = reactions;
  }

  public ArticleData toData() {
    ArticleData data = new ArticleData();
    data.setId(this.id);
    data.setContenu(this.contenu);
    data.setDatePublication(this.publication);
    if (this.auteur != null) {
      data.setAuteur(this.auteur.toData());
    }
    return data;
  }

}
