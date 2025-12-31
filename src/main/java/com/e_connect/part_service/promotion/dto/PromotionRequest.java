package com.e_connect.part_service.promotion.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
public class PromotionRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  private String promotionType;
  private Long languageId;
}
