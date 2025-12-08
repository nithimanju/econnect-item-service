package com.e_connect.part_service.itemdetail.dto;

import java.util.List;
import java.util.Map;

import com.e_connect.part_service.model.BaseItemDetail;
import com.e_connect.part_service.model.Media;

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
@EqualsAndHashCode(callSuper = true)
public class ItemDetailResponse extends BaseItemDetail {
  private static final long serialVersionUID = 1L;

  private List<Dealer> dealers;
  private Brand brand;

  @NoArgsConstructor
  @AllArgsConstructor
  @Data
  public static class Dealer {
    private String dealerId;
    private String dealerRef;
    private Map<String, String> dealerNames;
    private Map<String, String> dealerDescription;
    private List<Media> dealerMedias;
    private String dealerUrl;
  }

}
