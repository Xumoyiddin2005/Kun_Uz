package com.company.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false,name = "created_date")
    private LocalDateTime createdDate=LocalDateTime.now();
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(nullable = false)
    private boolean visible=true;
    @Column
    private Integer replayId=id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private ArticleEntity article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comment")
    private List<CommentLikeEntity> commentLikeList;

    public CommentEntity(Integer id) {
        this.id = id;
    }
}
