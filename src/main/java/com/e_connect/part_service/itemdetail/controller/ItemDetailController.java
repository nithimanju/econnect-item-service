package com.e_connect.part_service.itemdetail.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.e_connect.part_service.itemdetail.dto.ItemDetailResponse;
import com.e_connect.part_service.itemdetail.service.ItemDetailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ItemDetailController {

  private final ItemDetailService itemDetailService;

  @GetMapping(value = "/part-detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ItemDetailResponse> get(@PathVariable Long id) {
    log.debug("Request for fetching item-detail for item id: {}", id);
    ItemDetailResponse itemDetail = null;
    try {
      itemDetail = itemDetailService.get(id);
      if (ObjectUtils.isEmpty(itemDetail)) {
        log.warn("No item detail found for requested item-id : {}", id);
        return new ResponseEntity<>(itemDetail, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      log.error("Error occured while fetching item-detail for item id: {}", id, e);
      return new ResponseEntity<>(itemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    log.debug("Item detail {} for requested itemId: {}", itemDetail, id);
    return new ResponseEntity<>(itemDetail, HttpStatus.OK);
  }
}
