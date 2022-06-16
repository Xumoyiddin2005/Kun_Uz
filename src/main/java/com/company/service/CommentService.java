package com.company.service;

import com.company.dto.comment.CommentCreateDTO;
import com.company.dto.comment.CommentDTO;
import com.company.entity.ArticleEntity;
import com.company.entity.CommentEntity;
import com.company.entity.ProfileEntity;
import com.company.exps.ItemNotFoundException;
import com.company.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;


    @Autowired
    private ArticleService articleService;

    @Autowired
    private ProfileService profileService;

    public CommentCreateDTO create(CommentCreateDTO dto, Integer profileId) {
        CommentEntity entity = new CommentEntity();
        entity.setContent(dto.getContent());

        ArticleEntity article = articleService.get(dto.getArticleId());
        entity.setArticle(article);

        ProfileEntity profile = profileService.get(profileId);
        entity.setProfile(profile);


        commentRepository.save(entity);

        return dto;
    }

    public List<CommentDTO> getList() {

        Iterable<CommentEntity> all = commentRepository.findAllByVisible(true);
        List<CommentDTO> dtoList = new LinkedList<>();

        all.forEach(article -> dtoList.add(toDTO(article)));
        return dtoList;
    }

    public CommentDTO toDTO(CommentEntity entity) {
        CommentDTO dto = new CommentDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public void delete(Integer id) {
        Optional<CommentEntity> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new ItemNotFoundException("Mazgi bu idli comment yo'q");
        }
        CommentEntity entity = comment.get();
        entity.setVisible(false);
        commentRepository.save(entity);
    }

    public String update(Integer id, CommentDTO dto) {

        Optional<CommentEntity> optional = commentRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Bunday id li comment yo'q");
        }


        CommentEntity entity = optional.get();
        entity.setContent(dto.getContent());

        commentRepository.save(entity);

        return "Succesfully updated";
    }
}
