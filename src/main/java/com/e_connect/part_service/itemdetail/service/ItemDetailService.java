package com.e_connect.part_service.itemdetail.service;

import org.springframework.stereotype.Service;

import com.e_connect.part_service.itemdetail.repository.ItemDetailRepository;
import com.e_connect.part_service.model.ItemDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemDetailService {
   private final ItemDetailRepository itemDetailRepository;
   public ItemDetail get(String itemId) {
    return itemDetailRepository.findByItemId(itemId);
   }
}
