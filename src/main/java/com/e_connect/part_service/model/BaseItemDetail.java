package com.e_connect.part_service.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BaseItemDetail implements Serializable {

  private static final long serialVersionUID = 1L;

  private String itemId;
  private String itemNumber;
  private List<Media> medias;
  private List<ItemTitle> itemTitle;
  private List<LanguageDescription> itemDescriptions;
  private List<Category> parentCategories;
  private Brand brand;

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class LanguageDescription {
    private String languageCode;
    private List<String> description;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class ItemTitle {
    private String languageCode;
    private String title;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class Category {
    private String categoryId;
    private Map<String, String> categoryNames;
    private String categoryUrl;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class Brand {
    private String brandId;
    private Map<String, String> brandNames;
    private String brandUrl;
  }
}
