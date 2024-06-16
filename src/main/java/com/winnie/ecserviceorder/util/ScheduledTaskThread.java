package com.winnie.ecserviceorder.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.winnie.ecserviceorder.entity.ScheduledJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
public class ScheduledTaskThread implements Runnable {

  private ObjectMapper objectMapper;
  private RestTemplate restTemplate;
  private ScheduledJob scheduledJob;

  public ScheduledTaskThread(ScheduledJob scheduledJob) {
    this.scheduledJob = scheduledJob;
    this.restTemplate = new RestTemplate();
    objectMapper = new ObjectMapper();
  }

  public ScheduledTaskThread(ObjectMapper objectMapper, RestTemplate restTemplate, ScheduledJob scheduledJob) {
    this.objectMapper = objectMapper;
    this.restTemplate = restTemplate;
    this.scheduledJob = scheduledJob;
  }

  @Override
  public void run() {
    ResponseEntity<String> response = restTemplate.exchange(this.scheduledJob.getUrl(), this.getMethod(), this.buildHttpEntity(), String.class);
    log.info("Response status code: " + response.getStatusCodeValue());
    log.info("Response body: " + response.getBody());
  }

  private HttpEntity<String> buildHttpEntity() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);

    if (StringUtils.hasLength(this.scheduledJob.getHeader())) {
      try {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        Map<String, String> headerMap = objectMapper.readValue(this.scheduledJob.getHeader(), Map.class);
        multiValueMap.setAll(headerMap);
        httpHeaders.addAll(multiValueMap);
      } catch (Exception ex) {
        log.error(ex.getMessage(), ex);
      }
    }
    log.info("HttpHeaders: "+ httpHeaders);
    return new HttpEntity<>(this.scheduledJob.getBody(), httpHeaders);
  }

  private HttpMethod getMethod() {
    HttpMethod method;
    switch (this.scheduledJob.getMethod().toLowerCase()) {
      case "post":
        method = HttpMethod.POST;
        break;
      case "put":
        method = HttpMethod.PUT;
        break;
      case "delete":
        method = HttpMethod.DELETE;
        break;
      default:
        method = HttpMethod.GET;
    }

    return method;
  }
}
