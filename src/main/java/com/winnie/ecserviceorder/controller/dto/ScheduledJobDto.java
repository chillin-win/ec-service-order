package com.winnie.ecserviceorder.controller.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ScheduledJobDto {
  private String id;
  private String name;
  private String description;
  private String url;
  private String method;
  private String header;
  private String body;
  private String cron;
  private Map<String, String> extra;
}
