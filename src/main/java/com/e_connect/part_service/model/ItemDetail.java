package com.e_connect.part_service.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

import org.springframework.data.annotation.Id;

@Document(collection = "Part")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder(toBuilder = true)
public class ItemDetail extends BaseItemDetail {

  @Id
  private String id;
  private List<Dealer> dealers;

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class Dealer {
    private String dealerId;
    private String dealerRef;
  }
}
