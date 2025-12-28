package com.e_connect.part_service.branddetail.controller;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e_connect.part_service.branddetail.dto.BrandDetailResponse;
import com.e_connect.part_service.branddetail.service.BrandDetailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class BrandDetailController {

  private final BrandDetailService brandDetailService;

  @GetMapping(value = "/brand-detail/{brandId}")
  public ResponseEntity<BrandDetailResponse> get(@PathVariable Long brandId) {
    log.debug("Request for fetching Brand-detail for Brand id: {}", brandId);
    BrandDetailResponse brandDetailResponse = null;
    try {
      brandDetailResponse = brandDetailService.get(brandId);
      if (ObjectUtils.isEmpty(brandDetailResponse)) {
        log.warn("No Brand detail found for requested Brand-id : {}", brandId);
        return new ResponseEntity<>(brandDetailResponse, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      log.error("Error occured while fetching Brand-detail for Brand id: {}", brandId, e);
      return new ResponseEntity<>(brandDetailResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    log.debug("Brand detail {} for requested BrandId: {}", brandDetailResponse, brandId);
    return new ResponseEntity<>(brandDetailResponse, HttpStatus.OK);
  }

  @GetMapping(value = "/sub-brands/parent-id")
  public ResponseEntity<List<BrandDetailResponse>> getChildHierarchy(
      @RequestParam Long brandId, @RequestParam int from, @RequestParam int size) {
    log.debug("Request for fetching Sub Brands for Brand id: {}", brandId);
    List<BrandDetailResponse> brandDetailResponse = null;
    try {
      brandDetailResponse = brandDetailService.getList(brandId, from, size);
      if (ObjectUtils.isEmpty(brandDetailResponse)) {
        log.warn("No Sub Brands detail found for requested Brand-id : {}", brandId);
        return new ResponseEntity<>(brandDetailResponse, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      log.error("Error occured while fetching Sub Brands for Brand id: {}", brandId, e);
      return new ResponseEntity<>(brandDetailResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    log.debug("Sub Brands Heirarchies {} for requested BrandId: {}", brandDetailResponse, brandId);
    return new ResponseEntity<>(brandDetailResponse, HttpStatus.OK);
  }
}