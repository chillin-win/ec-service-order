package com.winnie.ecserviceorder.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/version")
public class VersionController {
  @Autowired
  private BuildProperties buildProperties;

  @Value("${instance.name}")
  private String instanceName;

  /**
   * Get current version of service.
   *
   * @return Current version
   */
  @Operation(summary = "Get version of this service")
  @GetMapping
  public ResponseEntity<Map<String, String>> getVersion() {
    Map<String, String> infoMap = new HashMap<>();
    infoMap.put("instanceName", instanceName);
    infoMap.put("version", buildProperties.getVersion());

    return ResponseEntity.ok(infoMap);
  }
}
