package com.e_connect.part_service.categorydetail.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.apache.commons.lang3.ObjectUtils;
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

  public Map<Integer, CategoryDetailResponse> getParentCategoryHiearchyById(String categoryId) {
    CategoryDetail categoryDetail = getCategoryById(categoryId);
    if (ObjectUtils.isEmpty(categoryDetail)) {
      return null;
    }
    TreeMap<Integer, CategoryDetailResponse> categoryDetailResponses = new TreeMap<>();
    categoryDetailResponses = buildParentResponse(categoryDetailResponses, categoryDetail, 0);
    return sort(categoryDetailResponses);
  }

  public Map<Integer, List<CategoryDetailResponse>> getChildCategoryHiearchyById(String categoryId) {
    Map<Integer, List<CategoryDetailResponse>> categoryChildMap = new HashMap<>();
    return buildChildResponse(categoryChildMap, categoryId, 0);
  }

  public CategoryDetailResponse getCategoryDetailById(String categoryId) {
    CategoryDetail categoryDetail = getCategoryById(categoryId);
    if (ObjectUtils.isEmpty(categoryDetail)) {
      return null;
    }
    return buildCategoryDetailResponse(categoryDetail);
  }

  private CategoryDetail getCategoryById(String categoryId) {
    Optional<CategoryDetail> categoryDetail = categoryDetailRepository.findByCategoryId(categoryId);
    if (ObjectUtils.isEmpty(categoryDetail)) {
      return null;
    }
    return categoryDetail.get();
  }

  private List<CategoryDetail> getCategoriesByParentId(String categoryId) {
    Optional<List<CategoryDetail>> categoryDetail = categoryDetailRepository.findByParentCategoryId(categoryId);
    if (ObjectUtils.isEmpty(categoryDetail)) {
      return null;
    }
    return categoryDetail.get();
  }

  private TreeMap<Integer, CategoryDetailResponse> buildParentResponse(
      TreeMap<Integer, CategoryDetailResponse> categoryDetailResponses, CategoryDetail categoryDetail, Integer level) {
    categoryDetailResponses.put(level, buildCategoryDetailResponse(categoryDetail));

    if (ObjectUtils.isNotEmpty(categoryDetail.getParentCategoryId())) {
      buildParentResponse(categoryDetailResponses, getCategoryById(categoryDetail.getParentCategoryId()), ++level);
    }
    return categoryDetailResponses;
  }

  private Map<Integer, List<CategoryDetailResponse>> buildChildResponse(
      Map<Integer, List<CategoryDetailResponse>> categoryMap, String categoryId, Integer level) {
    List<CategoryDetail> categories = getCategoriesByParentId(categoryId);
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

}
