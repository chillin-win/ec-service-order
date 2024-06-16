package com.winnie.ecserviceorder.entity;


import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
@Data
public class Delivery implements Persistable<String> {

  @Id
  private String id;

  @Column
  private String name;
  @Column
  private String address;
  @Column
  private LocalDateTime createdAt;
  @Column
  private String createdBy;
  @Column
  private LocalDateTime updatedAt;
  @Column
  private String updatedBy;

  @Transient
  private boolean isUpdated;

  @Override
  public boolean isNew() {
    return !isUpdated;
  }
}
