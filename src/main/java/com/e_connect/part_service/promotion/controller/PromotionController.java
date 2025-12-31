package com.e_connect.part_service.promotion.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.e_connect.part_service.promotion.dto.PromotionRequest;
import com.e_connect.part_service.promotion.dto.PromotionResponse;
import com.e_connect.part_service.promotion.service.PromotionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class PromotionController {
  private final PromotionService promotionService;

  @GetMapping(value = "/promotion/get")
  public ResponseEntity<List<PromotionResponse>> getAll(@RequestParam String promotionType,
      @RequestParam Long languageId) {
    List<PromotionResponse> response = new ArrayList<>();
    try {
      PromotionRequest promotionRequest = PromotionRequest.builder()
          .languageId(languageId)
          .promotionType(promotionType)
          .build();
      response = promotionService.getList(promotionRequest);
      if (ObjectUtils.isEmpty(response)) {
        log.warn("No Promotion found for promotionType: {}", promotionType);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      log.error("Error in fetching Promotion: ", e);
      return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
