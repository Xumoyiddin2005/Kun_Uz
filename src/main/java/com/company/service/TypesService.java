package com.company.service;

import com.company.dto.RegionDto;
import com.company.dto.article.TypesDTO;
import com.company.entity.TypesEntity;
import com.company.enums.Language;
import com.company.exps.AlreadyExist;
import com.company.exps.BadRequestException;
import com.company.exps.ItemNotFoundEseption;
import com.company.repository.TypeRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TypesService {

    private final TypeRepository typesRepository;

    public TypesService(TypeRepository typesRepository) {
        this.typesRepository = typesRepository;
    }

    public void create(TypesDTO typesDto) {

        Optional<TypesEntity> articleTypeEntity = typesRepository.findByKey(typesDto.getKey());

        if (articleTypeEntity.isPresent()) {
            throw new AlreadyExist("Already exist");
        }

        isValid(typesDto);


        TypesEntity entity = new TypesEntity();
        entity.setKey(typesDto.getKey());
        entity.setNameUz(typesDto.getNameUz());
        entity.setNameRu(typesDto.getNameRu());
        entity.setNameEn(typesDto.getNameEn());

        typesRepository.save(entity);
    }

    private void isValid(TypesDTO dto) {
        if (dto.getKey().length() < 4) {
            throw new BadRequestException("key to short");
        }

        if (dto.getNameUz() == null || dto.getNameUz().length() < 3) {
            throw new BadRequestException("wrong name uz");
        }

        if (dto.getNameRu() == null || dto.getNameRu().length() < 3) {
            throw new BadRequestException("wrong name ru");
        }

        if (dto.getNameEn() == null || dto.getNameEn().length() < 3) {
            throw new BadRequestException("wrong name en");
        }
    }

    public List<TypesDTO> getList(Language lang) {

        Iterable<TypesEntity> all = typesRepository.findAllByVisible(true);
        List<TypesDTO> dtoList = new LinkedList<>();

        all.forEach(typesEntity -> {
            TypesDTO dto = new TypesDTO();
            switch (lang) {
                case ru -> dto.setName(typesEntity.getNameRu());
                case en -> dto.setName(typesEntity.getNameEn());
                case uz -> dto.setName(typesEntity.getNameUz());
            }
            dtoList.add(dto);
        });
        return dtoList;
    }

    public List<TypesDTO> getListOnlyForAdmin() {

        Iterable<TypesEntity> all = typesRepository.findAll();
        List<TypesDTO> dtoList = new LinkedList<>();

        all.forEach(typesEntity -> {
            TypesDTO dto = new TypesDTO();
            dto.setKey(typesEntity.getKey());
            dto.setNameUz(typesEntity.getNameUz());
            dto.setNameRu(typesEntity.getNameRu());
            dto.setNameEn(typesEntity.getNameEn());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public void update(Integer id, RegionDto dto) {
        Optional<TypesEntity> articleTypeEntity = typesRepository.findById(id);

        if (articleTypeEntity.isEmpty()) {
            throw new ItemNotFoundEseption("not found articleType");
        }

        TypesEntity entity = articleTypeEntity.get();

        entity.setKey(dto.getKey());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        typesRepository.save(entity);
    }

    public void delete(Integer id) {

        Optional<TypesEntity> entity = typesRepository.findById(id);

        if (entity.isEmpty()) {
            throw new ItemNotFoundEseption("not found articleType");
        }

        if (entity.get().getVisible().equals(Boolean.FALSE)) {
            throw new AlreadyExist("this articleType already visible false");
        }

        TypesEntity articleType = entity.get();

        articleType.setVisible(Boolean.FALSE);

        typesRepository.save(articleType);
    }

    public PageImpl pagination(int page, int size) {

        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<TypesEntity> all = typesRepository.findAll(pageable);

        List<TypesEntity> list = all.getContent();

        List<TypesDTO> dtoList = new LinkedList<>();

        list.forEach(typesEntity -> {
            TypesDTO dto = new TypesDTO();
            dto.setId(typesEntity.getId());
            dto.setKey(typesEntity.getKey());
            dto.setNameEn(typesEntity.getNameEn());
            dto.setNameRu(typesEntity.getNameRu());
            dto.setNameUz(typesEntity.getNameUz());
            dtoList.add(dto);
        });

        return new PageImpl(dtoList, pageable, all.getTotalElements());

    }


}
