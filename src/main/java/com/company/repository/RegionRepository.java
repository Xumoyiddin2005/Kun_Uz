package com.company.repository;

import com.company.dto.RegionDto;
import com.company.entity.RegionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends CrudRepository<RegionEntity, Integer> {

    Optional<RegionDto> findByKey(String key);

    List<RegionEntity> findAllByVisible(Boolean b);

}
