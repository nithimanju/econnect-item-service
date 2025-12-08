package com.e_connect.part_service.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import org.springframework.data.annotation.Id;

@Document(collection = "Part")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
public class ItemDetail {

  @Id
  private String id;
  private String itemId;
  private String itemNumber;
  private String itemTitle;
  private List<Media> media;
  private Dealer dealer;
  private Brand brand;
  private List<LanguageDescription> descriptionList;
  private ParentCategory parentCategory;

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class Dealer {
    private String dealerId;
    private String delaerName;
    private String dealerUrl;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class Brand {
    private String brandId;
    private String brandName;
    private String brandUrl;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class Media {
    private String mediaId;
    private String mediaName;
    private String mediaUrl;
    private String mediaType;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class ParentCategory {
    private String categoryId;
    private String categoryRef;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class Description {
    private Integer order;
    private String htmlContent;
  }

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class LanguageDescription {
    private String languageCode;
    private List<Description> inLineDescriptions;
    private List<Description> outLineDescriptions;
  }
}
