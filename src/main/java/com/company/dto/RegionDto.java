package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDto {
    private Integer id;
    private String key;
    private String nameUz;
    private String nameRu;
    private String nameEn;

}
