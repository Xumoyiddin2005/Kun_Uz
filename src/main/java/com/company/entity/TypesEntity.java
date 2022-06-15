package com.company.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "types")
@NoArgsConstructor
public class TypesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String key;

    @Column(nullable = false, name = "name_uz")
    private String nameUz;

    @Column(nullable = false, name = "name_ru")
    private String nameRu;

    @Column(nullable = false, name = "name_en")
    private String nameEn;

    @Column(nullable = false)
    private Boolean visible = Boolean.TRUE;

    @Column(nullable = false, name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();



    public TypesEntity(Integer id) {
        this.id = id;
    }
}
