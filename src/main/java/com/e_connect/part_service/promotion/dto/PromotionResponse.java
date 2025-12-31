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
public class PromotionResponse implements Serializable {
  private static final long serialVersionUID = 1L;
  private String promotionName;
  private String promotionPath;
  private String mediaPath;
  private String mediaName;
  private Long priority;
}
