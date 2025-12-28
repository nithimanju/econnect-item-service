package com.e_connect.part_service.branddetail.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.e_connect.part_service.model.BrandDetail;

@Repository
public interface BrandDetailRepository extends MongoRepository<BrandDetail, String> {
  Page<BrandDetail> findByParentBrandIdIsNull(Pageable pageable);
  Optional<BrandDetail> findByBrandId(Long brandId);
  Page<BrandDetail> findByParentBrandId(Long brandId, Pageable pageable);
}
