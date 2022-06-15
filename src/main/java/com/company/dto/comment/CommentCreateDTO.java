package com.company.dto.comment;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommentCreateDTO {

    private String content;
    private String articleId;
    private Integer profileId;
}
