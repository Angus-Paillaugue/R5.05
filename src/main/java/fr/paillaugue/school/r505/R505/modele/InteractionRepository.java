package fr.paillaugue.school.r505.R505.modele;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface InteractionRepository extends CrudRepository<InteractionData, Integer> {
  List<InteractionData> findAllByArticle(ArticleData article);
}
