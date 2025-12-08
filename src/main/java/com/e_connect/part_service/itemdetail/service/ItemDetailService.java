package com.e_connect.part_service.itemdetail.service;

import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.e_connect.part_service.itemdetail.dto.ItemDetailResponse;
import com.e_connect.part_service.itemdetail.repository.ItemDetailRepository;
import com.e_connect.part_service.model.ItemDetail;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ItemDetailService {
  private final ItemDetailRepository itemDetailRepository;

  public ItemDetailResponse get(String itemId) {
    Optional<ItemDetail> optItemDetail = itemDetailRepository.findByItemId(itemId);
    if (ObjectUtils.isEmpty(optItemDetail)) {
      return null;
    }
    ItemDetail itemDetail = optItemDetail.get();
    return buildItemDetailResponse(itemDetail);
  }

  private ItemDetailResponse buildItemDetailResponse(ItemDetail itemDetail) {
    return ItemDetailResponse.builder()
        .itemId(itemDetail.getItemId())
        .itemNumber(itemDetail.getItemNumber())
        .itemTitles(itemDetail.getItemTitles())
        .itemDescriptions(itemDetail.getItemDescriptions())
        .parentCategories(itemDetail.getParentCategories())
        .brand(itemDetail.getBrand())
        .medias(itemDetail.getMedias())
        .rating(itemDetail.getRating())
        .availability(itemDetail.getAvailability())
        .currency(itemDetail.getCurrency())
        .price(itemDetail.getPrice())
        .discountPercentage(itemDetail.getDiscountPercentage())
        .build();
  }
}
