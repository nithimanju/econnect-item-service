package com.e_connect.part_service.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
  private Map<String, String> itemTitles;
  private Map<String, List<String>> itemDescriptions;
  private List<Category> parentCategories;
  private Brand brand;
  private float price;
  private String currency;
  private float discountPercentage;
  private float rating;
  private Availability availability;

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

  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @Getter
  public static class Availability {
    private String availabilityDescription;
    private int availabilityCount;
  }
}
