package com.e_connect.part_service.model;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseRecord {
  @Column(name = "CREATED_DATE")
  private Date createdDate;
  private Date creator;
  @Column(name = "MODIFIED_DATE")
  private Date modifiedDate;
  private Date modifier;
}
