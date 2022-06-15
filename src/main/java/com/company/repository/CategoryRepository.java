package com.company.repository;

import com.company.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Integer> {

    Optional<CategoryEntity> findByKey(String key);

    List<CategoryEntity> findAllByVisible(Boolean b);


}
