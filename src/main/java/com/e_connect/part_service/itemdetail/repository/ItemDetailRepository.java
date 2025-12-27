package com.e_connect.part_service.itemdetail.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.e_connect.part_service.model.ItemDetail;

@Repository
public interface ItemDetailRepository extends MongoRepository<ItemDetail, String> {
    Optional<ItemDetail> findByItemId(Long itemId);
}
