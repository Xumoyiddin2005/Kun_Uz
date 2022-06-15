package com.company.service;


import com.company.dto.CategoryDTO;
import com.company.dto.ProfileDTO;
import com.company.dto.article.ArticleCreateDTO;
import com.company.dto.article.ArticleDTO;
import com.company.entity.*;
import com.company.enums.ArticleStatus;
import com.company.exps.BadRequestException;
import com.company.exps.ItemNotFoundEseption;
import com.company.repository.ArticleRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final RegionService regionService;
    private final CategoryService categoryService;
    private final ArticleTypeService articleTypeService;
    private final ArticleTagService articleTagService;

    public ArticleService(ArticleRepository articleRepository, RegionService regionService, CategoryService categoryService, ArticleTypeService articleTypeService, ArticleTagService articleTagService) {
        this.articleRepository = articleRepository;
        this.regionService = regionService;
        this.categoryService = categoryService;
        this.articleTypeService = articleTypeService;
        this.articleTagService = articleTagService;
    }

    public ArticleCreateDTO create(ArticleCreateDTO dto, Integer profileId) {
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setDescription(dto.getDescription());

        RegionEntity region = regionService.get(dto.getRegionId());
        entity.setRegion(region);

        CategoryEntity category = categoryService.get(dto.getCategoryId());
        entity.setCategory(category);

        ProfileEntity moderator = new ProfileEntity();
        moderator.setId(profileId);
        entity.setModerator(moderator);
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);

        articleRepository.save(entity);


        articleTypeService.create(entity, dto.getTypesList()); // type

        articleTagService.create(entity, dto.getTagList());  // tag

        return dto;
    }

    public List<ArticleDTO> getArticles() {
        Iterable<ArticleEntity> all = articleRepository.findAllByVisible(true);

        List<ArticleDTO> articleDTOS = new LinkedList<>();
        all.forEach(categoryEntity -> articleDTOS.add(toDTO(categoryEntity)));
        return articleDTOS;
    }

    public ArticleDTO toDTO(ArticleEntity entity) {
        ArticleDTO dto = new ArticleDTO();
        dto.setUuid(entity.getId());
        dto.setContent(entity.getContent());
        dto.setDescription(entity.getDescription());
        dto.setVisible(entity.getVisible());
        dto.setViewCount(entity.getViewCount());
        dto.setTitle(entity.getTitle());
        dto.setStatus(entity.getStatus());
        dto.setPublishDate(entity.getPublishDate());
        dto.setCreatedDate(entity.getCreatedDate());


        dto.setRegion(regionService.toDTO(entity.getRegion()));
        dto.setCategory(toDTO2(entity.getCategory()));
        dto.setModeratorId(toDTO3(entity.getModerator()));
//        dto.setPublisherId(toDTO1(entity.getPublisher()));

        return dto;
    }

    public void delete(String id) {
        Optional<ArticleEntity> article = articleRepository.findById(id);
        if (article.isEmpty()) {
            throw new ItemNotFoundEseption("Mazgi bu id li article yo'q");
        }
        ArticleEntity entity = article.get();
        entity.setVisible(false);
        articleRepository.save(entity);
    }

    public String update(String id, ArticleCreateDTO dto) {
        isValid(dto);

        Optional<ArticleEntity> optional = articleRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ItemNotFoundEseption("Bunday id li article yo'q");
        }


        ArticleEntity entity = optional.get();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setDescription(dto.getDescription());

        RegionEntity region = regionService.get(dto.getRegionId());
        entity.setRegion(region);

        CategoryEntity category = categoryService.get(dto.getCategoryId());
        entity.setCategory(category);

        articleRepository.save(entity);

        articleTypeService.create(entity, dto.getTypesList()); // type

        articleTagService.create(entity, dto.getTagList());  // tag

        return "Successfully updated";

    }

    public ArticleEntity get(String id) {
        return articleRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundEseption("article not found");
        });
    }


    private void isValid(ArticleCreateDTO dto) {
        if (dto.getContent() == null) {
            throw new BadRequestException("Contenti xato.");
        }
        if (dto.getDescription() == null) {
            throw new BadRequestException("Description xato.");
        }
        if (dto.getTitle() == null) {
            throw new BadRequestException("Title xato");
        }

    }
    public ProfileDTO toDTO1(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurName(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        return dto;
    }

    public ProfileDTO toDTO3(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurName(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRole(entity.getRole());
        return dto;
    }

    public CategoryDTO toDTO2(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setNameEn(entity.getNameEn());
        dto.setNameRu(entity.getNameRu());
        dto.setNameUz(entity.getNameUz());
        return dto;
    }

    public PageImpl pagination(int page, int size) {

        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ArticleEntity> all = articleRepository.findAll(pageable);

        List<ArticleEntity> list = all.getContent();

        List<ArticleDTO> dtoList = new LinkedList<>();

        list.forEach(typesEntity -> {
            ArticleDTO dto = new ArticleDTO();
            dto.setModeratorId(toDTO3(typesEntity.getModerator()));
            dto.setCategory(toDTO2(typesEntity.getCategory()));
            dto.setRegion(regionService.toDTO(typesEntity.getRegion()));
            dto.setStatus(typesEntity.getStatus());
            dto.setTitle(typesEntity.getTitle());
            dto.setVisible(typesEntity.getVisible());
            dto.setUuid(typesEntity.getId());
            dto.setPublishDate(typesEntity.getPublishDate());
            dto.setSharedCount(typesEntity.getSharedCount());
            dto.setViewCount(typesEntity.getViewCount());
            dto.setContent(typesEntity.getContent());
            dto.setCreatedDate(typesEntity.getCreatedDate());
            dto.setDescription(typesEntity.getDescription());

            dtoList.add(dto);
        });

        return new PageImpl(dtoList, pageable, all.getTotalElements());
    }
}
