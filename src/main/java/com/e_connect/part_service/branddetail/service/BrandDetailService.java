package com.e_connect.part_service.branddetail.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.e_connect.part_service.branddetail.dto.BrandDetailResponse;
import com.e_connect.part_service.branddetail.repository.BrandDetailRepository;
import com.e_connect.part_service.categorydetail.dto.CategoryDetailResponse;
import com.e_connect.part_service.categorydetail.service.CategoryDetailService;
import com.e_connect.part_service.model.BrandDetail;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BrandDetailService {

  private final BrandDetailRepository brandDetailRepository;
  private final CategoryDetailService categoryDetailService;

  public BrandDetailResponse get(final Long brandId) {
    BrandDetail brandDetail = getByBrandId(brandId);
    if (ObjectUtils.isEmpty(brandDetail)) {
      return null;
    }
    List<BrandDetailResponse> brandDetailResponses = buildBrandDetailResponse(List.of(brandDetail));
    if (ObjectUtils.isEmpty(brandDetailResponses)) {
      return null;
    }
    return brandDetailResponses.get(0);
  }

  public List<BrandDetailResponse> getList(final Long brandId, final int from, final int size) {
    Page<BrandDetail> brandDetails = getCategoriesByParentId(brandId, PageRequest.of(from, size));
    if (ObjectUtils.isEmpty(brandDetails)) {
      return null;
    }
    List<BrandDetailResponse> brandDetailResponses = buildBrandDetailResponse(brandDetails.stream().toList());
    if (ObjectUtils.isEmpty(brandDetailResponses)) {
      return null;
    }
    return brandDetailResponses;
  }

  private BrandDetail getByBrandId(final Long brandId) {
    return brandDetailRepository.findByBrandId(brandId).orElse(null);
  }

  private Page<BrandDetail> getCategoriesByParentId(final Long brandId, final Pageable pageable) {
    Page<BrandDetail> categoryDetail = null;
    if (Long.valueOf(0).equals(brandId)) {
      categoryDetail = brandDetailRepository.findByParentBrandIdIsNull(pageable);
    } else {
      categoryDetail = brandDetailRepository.findByParentBrandId(brandId, pageable);
    }
    if (ObjectUtils.isEmpty(categoryDetail)) {
      return null;
    }
    return categoryDetail;
  }

  private List<BrandDetailResponse> buildBrandDetailResponse(final List<BrandDetail> brandDetails) {
    if (ObjectUtils.isEmpty(brandDetails)) {
      return null;
    }
    List<Long> categoryIds = brandDetails.stream().flatMap(brandDetail -> brandDetail.getChildCategoryIds().stream())
        .filter(ObjectUtils::isNotEmpty)
        .distinct().toList();
    List<CategoryDetailResponse> categoryDetailResponses = categoryDetailService.getCategoriesByIds(categoryIds);
    Map<Long, CategoryDetailResponse> categoryDetailResponseMap = new HashMap<>();
    if (ObjectUtils.isNotEmpty(categoryDetailResponses)) {
      categoryDetailResponseMap = categoryDetailResponses.stream()
          .collect(Collectors.toMap(CategoryDetailResponse::getCategoryId, categoryDetail -> categoryDetail));
    }
    return iterateResponses(brandDetails, categoryDetailResponseMap);
  }

  private List<BrandDetailResponse> iterateResponses(final List<BrandDetail> brandDetails,
      final Map<Long, CategoryDetailResponse> categoryDetailResponseMap) {
    List<BrandDetailResponse> brandDetailResponses = new LinkedList<>();
    brandDetails.forEach(brandDetail -> brandDetailResponses.add(BrandDetailResponse.builder()
        .brandId(brandDetail.getBrandId())
        .brandTitles(brandDetail.getBrandTitles())
        .brandDescriptions(brandDetail.getBrandDescriptions())
        .parentBrandId(brandDetail.getParentBrandId())
        .categories(getCategoryDetailsByIds(brandDetail.getChildCategoryIds(), categoryDetailResponseMap))
        .brandURLSlugs(brandDetail.getBrandURLSlugs())
        .medias(brandDetail.getMedias())
        .popularity(brandDetail.getPopularity())
        .build()));
    return brandDetailResponses;
  }

  private List<CategoryDetailResponse> getCategoryDetailsByIds(final List<Long> categoryIds,
      final Map<Long, CategoryDetailResponse> categoryDetailResponseMap) {

    if (ObjectUtils.isEmpty(categoryIds)) {
      return null;
    }
    return categoryIds.stream()
        .map(categoryId -> categoryDetailResponseMap.get(categoryId))
        .filter(categoryDetailResponse -> ObjectUtils.isNotEmpty(categoryDetailResponse))
        .toList();
  }
}
