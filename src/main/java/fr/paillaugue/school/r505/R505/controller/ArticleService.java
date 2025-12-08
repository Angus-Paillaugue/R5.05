package fr.paillaugue.school.r505.R505.controller;

import java.sql.Date;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.paillaugue.school.r505.R505.modele.ArticleData;
import fr.paillaugue.school.r505.R505.modele.ArticleRepository;
import fr.paillaugue.school.r505.R505.modele.Roles;
import fr.paillaugue.school.r505.R505.modele.UserData;
import fr.paillaugue.school.r505.R505.modele.UserRepository;

@Service
public class ArticleService {
  @Autowired
  private ArticleRepository repository;
  @Autowired
  private UserService userService;
  @Autowired
  private UserRepository userRepository;

  public Integer createArticle(String content, Integer userId) {
    User user = userService.findById(userId);
    if (user == null) {
        throw new IllegalArgumentException("User with id " + userId + " does not exist");
    }
    Article article = new Article();
    article.setContenu(content);
    article.setDatePublication(new Date(System.currentTimeMillis()));

    ArticleData articleData = article.toData();
    UserData userData = userRepository.findById(userId).orElseThrow(() ->
        new IllegalArgumentException("User not found"));
    articleData.setAuteur(userData);

    ArticleData savedArticle = repository.saveAndFlush(articleData);
    return savedArticle.getId();
  }

  public Article findById(Integer id) {
    return repository.findById(id).map(Article::new).orElse(null);
  }

  public Collection<Article> getAll() {
    return repository.findAll().stream().map(Article::new).toList();
  }

  public void deleteById(int id, Integer userId) throws Exception {
    Article article = this.findById(id);
    User user = userService.findById(userId);
    if (article == null) {
      throw new Exception("Article not found");
    }
    if (!article.getAuteur().getId().equals(userId) && user.getRole() != Roles.moderator) {
      throw new Exception("You are not authorized to delete this article");
    }
    repository.deleteById(id);
  }

  public void update(Integer id, Integer userId, String newContent) throws Exception {
    Article article = this.findById(id);
    User user = userService.findById(userId);
    if (article == null) {
      throw new Exception("Article not found");
    }
    if (!article.getAuteur().getId().equals(userId) && user.getRole() != Roles.moderator) {
      throw new Exception("You are not authorized to update this article");
    }
    article.setContenu(newContent);
    repository.saveAndFlush(article.toData());
  }

}
