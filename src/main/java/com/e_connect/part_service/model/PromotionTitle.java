package com.e_connect.part_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "PROMOTION_TITLE")
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class PromotionTitle extends BaseRecord {
  @EmbeddedId
  private PromotionLanguageId promotionLanguageId;
  private String title;
  private String description;
  private Boolean active;
  @ManyToOne
  @JoinColumn(name = "PROMOTION_ID", insertable = false, updatable = false)
  private Promotion promotion;

  @Embeddable
  @Builder(toBuilder = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @EqualsAndHashCode
  @ToString
  public static class PromotionLanguageId {
    @Column(name = "PROMOTION_ID")
    private Long promotionId;
    @Column(name = "LANGUAGE_ID")
    private Long languageId;
  }
}
