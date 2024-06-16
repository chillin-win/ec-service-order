package com.winnie.ecserviceorder.entity;

import lombok.Data;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(name = "scheduled_job")
@Data
public class ScheduledJob implements Persistable<String> {

  @Id
  private String id;

  @Column
  private String name;
  @Column
  private String description;
  @Column
  private String url;
  @Column
  private String method;
  @Column
  private String header;
  @Column
  private String body;
  @Column
  private String cron;

  @Transient
  private boolean isUpdated;

  @Override
  public boolean isNew() {
    return !isUpdated;
  }
}
