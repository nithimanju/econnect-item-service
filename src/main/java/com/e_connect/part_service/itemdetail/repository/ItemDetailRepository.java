package com.e_connect.part_service.itemdetail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.e_connect.part_service.model.ItemDetail;

@Repository
public interface ItemDetailRepository extends MongoRepository<ItemDetail, String> {
    public ItemDetail findByItemId(String itemId);
}
