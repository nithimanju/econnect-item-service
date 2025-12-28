package com.e_connect.part_service.branddetail.dto;

import java.util.List;

import com.e_connect.part_service.categorydetail.dto.CategoryDetailResponse;
import com.e_connect.part_service.model.BaseBrandDetail;

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
public class BrandDetailResponse extends BaseBrandDetail {
    private List<CategoryDetailResponse> categories;
}
