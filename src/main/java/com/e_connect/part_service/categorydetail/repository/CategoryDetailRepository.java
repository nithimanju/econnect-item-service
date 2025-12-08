package com.e_connect.part_service.categorydetail.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.e_connect.part_service.model.CategoryDetail;

@Repository
public interface CategoryDetailRepository extends MongoRepository<CategoryDetail, String> {
    Optional<List<CategoryDetail>> findByParentCategoryId(String categoryId);
    Optional<CategoryDetail> findByCategoryId(String categoryId);
}
