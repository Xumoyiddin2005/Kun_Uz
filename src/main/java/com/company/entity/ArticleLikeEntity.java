package com.company.entity;

import com.company.enums.LikeStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "article_like")
public class ArticleLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LikeStatus status;
    @JoinColumn(name = "article_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ArticleEntity article;
    @JoinColumn(name = "profile_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProfileEntity profile;
}