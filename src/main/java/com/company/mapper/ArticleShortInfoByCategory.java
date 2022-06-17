package com.company.mapper;

import java.time.LocalDateTime;

public interface ArticleShortInfoByCategory {
    String getId();
    String getTitle();
    String getDescription();
    LocalDateTime getPublishDate();
}
