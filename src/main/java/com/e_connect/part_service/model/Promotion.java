package com.e_connect.part_service.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "PROMOTION")
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class Promotion extends BaseRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PROMOTION_ID")
  private Long promotionId;
  @Column(name = "START_DATE")
  private Date startDate;
  @Column(name = "END_DATE")
  private Date endDate;
  @Column(name = "PROMOTION_pATH")
  private String promotionPath;
  private Long priority;
  private Boolean active;
  @ManyToOne
  @JoinColumn(name = "PROMOTION_TYPE_ID", insertable = false, updatable = false)
  private PromotionType promotionType;
  @OneToOne
  @JoinColumn(name = "MEDIA_ID", insertable = false, updatable = false)
  private MediaEntity media;
  @OneToMany(mappedBy = "promotion")
  private List<PromotionTitle> promotionTitles;
}
