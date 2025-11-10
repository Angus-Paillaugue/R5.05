package fr.paillaugue.school.r505.R505.modele;

import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called articleRepository
// CRUD refers Create, Read, Update, Delete

public interface ArticleRepository extends JpaRepository<ArticleData, Integer> {

}
