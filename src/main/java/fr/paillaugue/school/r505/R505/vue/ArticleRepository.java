package fr.paillaugue.school.r505.R505.vue;

import org.springframework.data.repository.CrudRepository;

import fr.paillaugue.school.r505.R505.modele.Article;

// This will be AUTO IMPLEMENTED by Spring into a Bean called articleRepository
// CRUD refers Create, Read, Update, Delete

public interface ArticleRepository extends CrudRepository<Article, Integer> {

}
