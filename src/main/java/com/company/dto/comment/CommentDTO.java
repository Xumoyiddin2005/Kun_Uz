package com.company.dto.comment;

import com.company.dto.ProfileDTO;
import com.company.dto.article.ArticleDTO;
import com.company.entity.ArticleEntity;
import com.company.entity.CommentLikeEntity;
import com.company.entity.ProfileEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter

public class CommentDTO {

    private Integer id;

    private String content;

    private LocalDateTime createdDate=LocalDateTime.now();

    private LocalDateTime updatedDate;

    private boolean visible=true;

    private Integer replayId=id;

    private ArticleDTO article;

    private ProfileDTO profile;

    private List<CommentLikeEntity> commentLikeList;
}
