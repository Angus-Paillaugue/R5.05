package fr.paillaugue.school.r505.R505.modele;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class ArticleData {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String contenu;

  private Date publication;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "parent_id")
  private UserData auteur;

  @JsonManagedReference
  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<InteractionData> interactions;

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

  public UserData getAuteur() {
    return this.auteur;
  }

  public void setAuteur(UserData user) {
    this.auteur = user;
  }

  public List<InteractionData> getInteractions() {
    return interactions;
  }

  public void setInteractions(List<InteractionData> interactions) {
    this.interactions = interactions;
  }
}
