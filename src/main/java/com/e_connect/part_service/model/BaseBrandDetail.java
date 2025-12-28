package com.e_connect.part_service.model;

import java.util.List;
import java.util.Map;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder(toBuilder = true)
@Getter
public class BaseBrandDetail implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long brandId;
  private Map<String, String> brandTitles;
  private Map<String, List<String>> brandDescriptions;
  private Long parentBrandId;
  private List<Media> medias;
  private List<Long> childCategoryIds;
  private List<String> brandURLSlugs;
  private Long popularity;
}
