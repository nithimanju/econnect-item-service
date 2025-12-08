package com.e_connect.part_service.categorydetail.dto;

import com.e_connect.part_service.model.BaseCategoryDetail;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryDetailResponse extends BaseCategoryDetail{
    private String rootCategoryRef;
}
