package fr.paillaugue.school.r505.R505.vue;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.paillaugue.school.r505.R505.modele.Article;
import fr.paillaugue.school.r505.R505.modele.Interaction;

public interface InteractionRepository extends CrudRepository<Interaction, Integer> {
  List<Interaction> findAllByArticle(Article article);
}
