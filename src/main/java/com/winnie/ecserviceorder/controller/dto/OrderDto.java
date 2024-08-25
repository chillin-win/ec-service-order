package com.winnie.ecserviceorder.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {

  private String name;
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;

}
