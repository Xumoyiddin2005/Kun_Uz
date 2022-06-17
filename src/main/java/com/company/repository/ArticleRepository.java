package com.company.repository;

import com.company.entity.ArticleEntity;
import com.company.enums.ArticleStatus;
import com.company.enums.LikeStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends PagingAndSortingRepository<ArticleEntity, String> {

    Iterable<ArticleEntity> findAllByVisible(boolean visible);

    List<ArticleEntity> findAllByVisible(Boolean visible);

    @Query(value = "SELECT art.* " +
            " FROM article_type as a " +
            " inner join article as art on art.id = a.article_id " +
            " inner join types as t on t.id = a.types_id " +
            " Where  t.key =:key  " +
            " order by art.publish_date " +
            " limit 5",
            nativeQuery = true)
    List<ArticleEntity> findTop5ByArticleNative(@Param("key") String key);

    @Modifying
    @Transactional
    @Query("update ArticleEntity a set a.status =:status, a.publishDate =:time, a.publisher.id=:pid where a.id=:articleId")
    void changeStatusToPublish(@Param("articleId") String articleId, @Param("pid") Integer pId,
                               @Param("status") ArticleStatus status, @Param("time") LocalDateTime time);

    @Modifying
    @Transactional
    @Query("update ArticleEntity a set a.status =:status where a.id=:articleId")
    void changeStatusNotPublish(@Param("articleId") String articleId, @Param("pid") Integer pId);

}