package com.company.dto.article;

import com.company.dto.ProfileDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class SavedArticleDTO {
    private Integer id;
    private ArticleDTO article;
    private ProfileDTO profileDTO;
    private LocalDateTime created_date;
}
