package fr.paillaugue.school.r505.R505.modele;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<ReactionData, Integer> {
  List<ReactionData> findAllByArticle(ArticleData article);
  Optional<ReactionData> findByArticleIdAndUserId(Integer articleId, Integer userId);
}
