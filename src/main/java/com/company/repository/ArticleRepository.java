package com.company.repository;

import com.company.entity.ArticleEntity;
import com.company.enums.LikeStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends PagingAndSortingRepository<ArticleEntity, String> {

        Iterable<ArticleEntity> findAllByVisible(boolean visible);

        List<ArticleEntity> findAllByVisible(Boolean visible);

       Optional<ArticleEntity> findById(String id);

}

