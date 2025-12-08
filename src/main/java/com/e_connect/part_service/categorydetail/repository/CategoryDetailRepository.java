package com.e_connect.part_service.categorydetail.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.e_connect.part_service.model.CategoryDetail;

@Repository
public interface CategoryDetailRepository extends MongoRepository<CategoryDetail, String> {
    Page<CategoryDetail> findByParentCategoryId(String categoryId, Pageable pageable);
    Optional<CategoryDetail> findByCategoryId(String categoryId);
    Page<CategoryDetail> findByParentCategoryIdIsNull(Pageable pageable);
}
