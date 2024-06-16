package com.winnie.ecserviceorder.entity;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order implements Persistable<String> {

  @Id
  private String id;

  @Column
  private String name;
  @Column
  private LocalDateTime createdAt;
  @Column
  private String createdBy;
  @Column
  private LocalDateTime updatedAt;
  @Column
  private String updatedBy;
  @Column
  private String notation;
  @Column
  private String status;

  @Transient
  private boolean isUpdated;

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public boolean isNew() {
    return !isUpdated;
  }
}
