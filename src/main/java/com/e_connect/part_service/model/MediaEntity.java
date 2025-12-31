package com.e_connect.part_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "MEDIA")
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class MediaEntity extends BaseRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEDIA_ID")
  private Long mediaId;
  @Column(name = "MEDIA_NAME")
  private String mediaName;
  @Column(name = "MEDIA_TYPE_ID")
  private Long mediaTypeId;
  @Column(name = "MEDIA_PATH")
  private String mediaPath;
  @Column(name = "MEDIA_LOCATION_ID")
  private Long mediaLocationId;
  private Boolean active;
}