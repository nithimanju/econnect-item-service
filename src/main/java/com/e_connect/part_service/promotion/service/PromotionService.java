package com.e_connect.part_service.promotion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.e_connect.part_service.model.QMediaEntity;
import com.e_connect.part_service.model.QPromotion;
import com.e_connect.part_service.model.QPromotionTitle;
import com.e_connect.part_service.model.QPromotionType;
import com.e_connect.part_service.promotion.dto.PromotionRequest;
import com.e_connect.part_service.promotion.dto.PromotionResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PromotionService {
  private final EntityManager entityManager;

  public List<PromotionResponse> getList(PromotionRequest promotionRequest) {
    List<PromotionResponse> response = new ArrayList<>();
    QPromotion prom = new QPromotion("prom");
    QPromotionTitle promTitle = new QPromotionTitle("promTitle");
    QPromotionTitle promTitleDefault = new QPromotionTitle("promTitleDefault");
    QPromotionType promType = new QPromotionType("promType");
    QMediaEntity media = new QMediaEntity("media");

    Long requestedLanguageId = promotionRequest.getLanguageId();
    Long defaultLanguageId = 1L;

    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

    response = queryFactory
        .select(Projections.constructor(PromotionResponse.class, promTitle.title.coalesce(promTitleDefault.title),
            prom.promotionPath,
            media.mediaPath, media.mediaName, prom.priority))
        .from(prom)
        .join(promType)
        .on(promType.active.isTrue().and(promType.promotionType.eq(promotionRequest.getPromotionType())))
        .leftJoin(promTitle)
        .on(promTitle.promotionLanguageId.promotionId.eq(prom.promotionId)
            .and(promTitle.promotionLanguageId.languageId.eq(requestedLanguageId))
            .and(promTitle.active.isTrue()))
        .leftJoin(promTitleDefault)
        .on(promTitleDefault.promotionLanguageId.promotionId.eq(prom.promotionId)
            .and(promTitleDefault.promotionLanguageId.languageId.eq(defaultLanguageId))
            .and(promTitleDefault.active.isTrue()))
        .leftJoin(media)
        .on(media.mediaId.eq(prom.media.mediaId).and(media.active.isTrue()))
        .where(prom.active.isTrue())
        .fetch();
    return response;
  }
}
