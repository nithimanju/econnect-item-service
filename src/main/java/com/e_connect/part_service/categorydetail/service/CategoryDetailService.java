package com.e_connect.part_service.categorydetail.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.e_connect.part_service.categorydetail.dto.CategoryDetailResponse;
import com.e_connect.part_service.categorydetail.repository.CategoryDetailRepository;
import com.e_connect.part_service.model.CategoryDetail;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CategoryDetailService {

  private final CategoryDetailRepository categoryDetailRepository;

  public Map<Integer, CategoryDetailResponse> getParentCategoryHiearchyById(Long categoryId) {
    CategoryDetail categoryDetail = getCategoryById(categoryId);
    if (ObjectUtils.isEmpty(categoryDetail)) {
      return null;
    }
    TreeMap<Integer, CategoryDetailResponse> categoryDetailResponses = new TreeMap<>();
    categoryDetailResponses = buildParentResponse(categoryDetailResponses, categoryDetail, 0);
    return sort(categoryDetailResponses);
  }

  public Map<Integer, List<CategoryDetailResponse>> getChildCategoryHiearchyById(Long categoryId) {
    return null;
  }

  public CategoryDetailResponse getCategoryDetailById(Long categoryId) {
    CategoryDetail categoryDetail = getCategoryById(categoryId);
    if (ObjectUtils.isEmpty(categoryDetail)) {
      return null;
    }
    return buildCategoryDetailResponse(categoryDetail);
  }

  private CategoryDetail getCategoryById(Long categoryId) {
    return categoryDetailRepository.findByCategoryId(categoryId).orElse(null);
  }

  public List<CategoryDetailResponse> getChildHierarchy(Long categoryId, int from, int size) {
    Pageable pageable = PageRequest.of(from, size);
    Page<CategoryDetail> categoryDetails = getCategoriesByParentId(categoryId, pageable);
    if(ObjectUtils.isEmpty(categoryDetails)){
      return null;
    }
    return categoryDetails.stream().map(category -> buildCategoryDetailResponse(category)).toList();
  }

  private Page<CategoryDetail> getCategoriesByParentId(Long categoryId, Pageable pageable) {
    Page<CategoryDetail> categoryDetail = null;
    if(Long.valueOf(0).equals(categoryId)){
      categoryDetail = categoryDetailRepository.findByParentCategoryIdIsNull(pageable);
    } else {
      categoryDetail = categoryDetailRepository.findByParentCategoryId(categoryId, pageable);
    }
    if (ObjectUtils.isEmpty(categoryDetail)) {
      return null;
    }
    return categoryDetail;
  }

  private TreeMap<Integer, CategoryDetailResponse> buildParentResponse(
      TreeMap<Integer, CategoryDetailResponse> categoryDetailResponses, CategoryDetail categoryDetail, Integer level) {
    categoryDetailResponses.put(level, buildCategoryDetailResponse(categoryDetail));

    if (ObjectUtils.isNotEmpty(categoryDetail.getParentCategoryId())) {
      buildParentResponse(categoryDetailResponses, getCategoryById(categoryDetail.getParentCategoryId()), ++level);
    }
    return categoryDetailResponses;
  }
  @SuppressWarnings("unused")
  private Map<Integer, List<CategoryDetailResponse>> buildChildResponse(
      Map<Integer, List<CategoryDetailResponse>> categoryMap, Long categoryId, Integer level) {
    List<CategoryDetail> categories = new ArrayList<>();
    if (ObjectUtils.isEmpty(categories)) {
      return categoryMap;
    }
    categoryMap.put(level, categories.stream().map(category -> buildCategoryDetailResponse(category)).toList());
    level = level + 1;
    for (CategoryDetail categoryDetail : categories) {
      buildChildResponse(categoryMap, categoryDetail.getCategoryId(), level);
    }
    return categoryMap;
  }

  private CategoryDetailResponse buildCategoryDetailResponse(CategoryDetail categoryDetail) {
    return CategoryDetailResponse.builder()
        .categoryId(categoryDetail.getCategoryId())
        .categoryNames(categoryDetail.getCategoryNames())
        .categoryMedias(categoryDetail.getCategoryMedias())
        .categoryDescriptions(categoryDetail.getCategoryDescriptions())
        .categoryUrl(categoryDetail.getCategoryUrl())
        .rootCategoryRef(categoryDetail.getParentCategoryId())
        .build();
  }

  private Map<Integer, CategoryDetailResponse> sort(TreeMap<Integer, CategoryDetailResponse> categoryMap) {
    Map<Integer, CategoryDetailResponse> normalized = new LinkedHashMap<>();
    int index = 0;
    for (CategoryDetailResponse value : categoryMap.descendingMap().values()) {
      normalized.put(index++, value);
    }
    return normalized;
  }

  public List<CategoryDetailResponse> getCategoriesByIds(List<Long> categoryIds) {
    List<CategoryDetail> categoryDetails = categoryDetailRepository.findByCategoryIdIn(categoryIds).orElse(null);
    if(ObjectUtils.isEmpty(categoryDetails)){
      return null;
    }
    return categoryDetails.stream().map(category -> buildCategoryDetailResponse(category)).toList();
  }
}
