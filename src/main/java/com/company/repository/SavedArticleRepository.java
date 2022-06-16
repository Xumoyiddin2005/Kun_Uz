package com.company.repository;

import com.company.entity.ArticleLikeEntity;
import com.company.entity.SavedArticleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SavedArticleRepository extends CrudRepository<SavedArticleEntity, Integer> {
    @Query("FROM SavedArticleEntity a where  a.article.id=:articleId and a.profile.id =:profileId")
    Optional<SavedArticleEntity> findExists(String articleId, Integer profileId);

    Optional<SavedArticleEntity> findById(String integer);
}
