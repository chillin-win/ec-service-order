package com.winnie.ecserviceorder.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryDto {

  private String id;
  private String name;
  private String address;
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;

}
