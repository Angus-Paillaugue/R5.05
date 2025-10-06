package fr.paillaugue.school.r505.R505.modele;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called articleRepository
// CRUD refers Create, Read, Update, Delete

public interface ArticleRepository extends CrudRepository<ArticleData, Integer> {

}
