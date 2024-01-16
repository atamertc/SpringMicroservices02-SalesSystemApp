package com.atamertc.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.elasticsearch.annotations.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseEntity {
    private Long createDate;
    private Long updateDate;
}
