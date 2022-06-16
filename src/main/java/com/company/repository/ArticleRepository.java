package com.company.repository;

import com.company.entity.ArticleEntity;
import com.company.enums.LikeStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

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

}