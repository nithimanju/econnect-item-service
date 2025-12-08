package com.e_connect.part_service.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@SuperBuilder(toBuilder = true)
@Getter
public class BaseCategoryDetail implements Serializable {

  private static final long serialVersionUID = 1L;

  private String categoryId;
  private List<CategoryName> categoryNames;
  private List<CategoryDescription> categoryDescriptions;
  private List<Media> categoryMedias;
  private String categoryUrl;

  @Builder(toBuilder = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class CategoryDescription {
    private String languageCode;
    private List<String> descriptions;
  }

  @Builder(toBuilder = true)
  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  public static class CategoryName {
    private String languageCode;
    private String title;
  }
}
