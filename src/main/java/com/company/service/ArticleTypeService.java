package com.company.service;

import com.company.dto.article.ArticleDTO;
import com.company.entity.ArticleEntity;
import com.company.entity.ArticleTypeEntity;
import com.company.entity.TypesEntity;
import com.company.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    public void create(ArticleEntity article, List<Integer> typesList) {
        for (Integer typesId : typesList) {

            ArticleTypeEntity articleTypeEntity = new ArticleTypeEntity();
            articleTypeEntity.setArticle(article);
            articleTypeEntity.setTypes(new TypesEntity(typesId));

            articleTypeRepository.save(articleTypeEntity);
        }
    }
}
