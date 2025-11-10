package fr.paillaugue.school.r505.R505.controller;

import java.sql.Date;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.paillaugue.school.r505.R505.modele.ArticleData;
import fr.paillaugue.school.r505.R505.modele.ArticleRepository;

@Service
public class ArticleService {
  @Autowired
  private ArticleRepository repository;
  @Autowired
  private UserService userService;

  public Integer createArticle(String content, Integer userId) {
    User user = userService.findById(userId);
    if (user == null) {
        throw new IllegalArgumentException("L'utilisateur avec l'id " + userId + " n'existe pas.");
    }
    Article article = new Article();
    article.setContenu(content);
    article.setAuteur(user); // Set the author
    article.setDatePublication(new Date(System.currentTimeMillis()));
    ArticleData articleData = repository.saveAndFlush(article.toData());

    return articleData.getId();
  }

  public Article findById(Integer id) {
    return repository.findById(id).map(Article::new).orElse(null);
  }

  public Collection<Article> getAll() {
    return repository.findAll().stream().map(Article::new).toList();
  }

  public void deleteById(int id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
  }

  public void update(Article a) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

}
