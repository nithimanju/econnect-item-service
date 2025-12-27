package com.e_connect.part_service.categorydetail.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e_connect.part_service.categorydetail.dto.CategoryDetailResponse;
import com.e_connect.part_service.categorydetail.service.CategoryDetailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
public class CategoryDetailController {

  private final CategoryDetailService categoryDetailService;

  @GetMapping(value = "/category-detail/{categoryId}")
  public ResponseEntity<CategoryDetailResponse> get(@PathVariable Long categoryId) {
    log.debug("Request for fetching Category-detail for Category id: {}", categoryId);
    CategoryDetailResponse categoryDetailResponse = null;
    try {
      categoryDetailResponse = categoryDetailService.getCategoryDetailById(categoryId);
      if (ObjectUtils.isEmpty(categoryDetailResponse)) {
        log.warn("No Category detail found for requested Category-id : {}", categoryId);
        return new ResponseEntity<>(categoryDetailResponse, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      log.error("Error occured while fetching Category-detail for Category id: {}", categoryId, e);
      return new ResponseEntity<>(categoryDetailResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    log.debug("Category detail {} for requested CategoryId: {}", categoryDetailResponse, categoryId);
    return new ResponseEntity<>(categoryDetailResponse, HttpStatus.OK);
  }

  @GetMapping(value = "/category-detail/parent-id/{categoryId}")
  public ResponseEntity<Map<Integer, List<CategoryDetailResponse>>> getChildHierarchies(
      @PathVariable Long categoryId) {
    log.debug("Request for fetching Child Category-hierarchies for Category id: {}", categoryId);
    Map<Integer, List<CategoryDetailResponse>> categoryDetailResponse = null;
    try {
      categoryDetailResponse = categoryDetailService.getChildCategoryHiearchyById(categoryId);
      if (ObjectUtils.isEmpty(categoryDetailResponse)) {
        log.warn("No Child Category detail found for requested Category-id : {}", categoryId);
        return new ResponseEntity<>(categoryDetailResponse, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      log.error("Error occured while fetching Child Category-hierarchies for Category id: {}", categoryId, e);
      return new ResponseEntity<>(categoryDetailResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    log.debug("Child Category Heirarchies {} for requested CategoryId: {}", categoryDetailResponse, categoryId);
    return new ResponseEntity<>(categoryDetailResponse, HttpStatus.OK);
  }

  @GetMapping(value = "/category-detail/child-id/{categoryId}")
  public ResponseEntity<Map<Integer, CategoryDetailResponse>> getParentHierarchies(
      @PathVariable Long categoryId) {
    log.debug("Request for fetching Parent Category-hierarchies for Category id: {}", categoryId);
    Map<Integer, CategoryDetailResponse> categoryDetailResponse = null;
    try {
      categoryDetailResponse = categoryDetailService.getParentCategoryHiearchyById(categoryId);
      if (ObjectUtils.isEmpty(categoryDetailResponse)) {
        log.warn("No Parent Category hierarchy found for requested Category-id : {}", categoryId);
        return new ResponseEntity<>(categoryDetailResponse, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      log.error("Error occured while fetching Parent Category-hierarchies for Category id: {}", categoryId, e);
      return new ResponseEntity<>(categoryDetailResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    log.debug("Parent Category hierarchies {} for requested CategoryId: {}", categoryDetailResponse, categoryId);
    return new ResponseEntity<>(categoryDetailResponse, HttpStatus.OK);
  }

  @GetMapping(value = "/child-hierarchy/parent-id")
  public ResponseEntity<List<CategoryDetailResponse>> getChildHierarchy(
      @RequestParam Long categoryId, @RequestParam int from, @RequestParam int size) {
    log.debug("Request for fetching Child Category-hierarchies for Category id: {}", categoryId);
    List<CategoryDetailResponse> categoryDetailResponse = null;
    try {
      categoryDetailResponse = categoryDetailService.getChildHierarchy(categoryId, from, size);
      if (ObjectUtils.isEmpty(categoryDetailResponse)) {
        log.warn("No Child Category detail found for requested Category-id : {}", categoryId);
        return new ResponseEntity<>(categoryDetailResponse, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      log.error("Error occured while fetching Child Category-hierarchies for Category id: {}", categoryId, e);
      return new ResponseEntity<>(categoryDetailResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    log.debug("Child Category Heirarchies {} for requested CategoryId: {}", categoryDetailResponse, categoryId);
    return new ResponseEntity<>(categoryDetailResponse, HttpStatus.OK);
  }
}
