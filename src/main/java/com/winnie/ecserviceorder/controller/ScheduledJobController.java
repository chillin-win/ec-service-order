package com.winnie.ecserviceorder.controller;

import com.winnie.ecserviceorder.controller.dto.ScheduledJobDto;
import com.winnie.ecserviceorder.service.ScheduledJobService;
import com.winnie.ecserviceorder.usecase.CreateScheduledJobUseCase;
import com.winnie.ecserviceorder.util.ScheduledJobMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/scheduledJobs")
@Slf4j
public class ScheduledJobController {

  @Autowired
  private CreateScheduledJobUseCase createScheduledJobUseCase;

  @Operation(summary = "Create scheduled job")
  @PostMapping("/")
  public ResponseEntity<ScheduledJobDto> createScheduledJob(
      @RequestBody ScheduledJobDto scheduledJobDto
  ) {
    return ResponseEntity.ok(ScheduledJobMapper.toDto(createScheduledJobUseCase.create(scheduledJobDto)));
  }

  @Operation(summary = "Scheduled job GET")
  @GetMapping("/test-get")
  public ResponseEntity<String> scheduledJobGet(
  ) {
    log.info("Calling Scheduled Job GET");
    return ResponseEntity.ok("Scheduled Job GET");
  }

  @Operation(summary = "Scheduled job POST")
  @PostMapping("/test-post")
  public ResponseEntity<String> scheduledJobPost(
      @RequestBody Map<String, String> tempBody,
      @RequestHeader("Authorization") String authorization
  ) {
    log.info("Calling Scheduled Job POST");
    log.info("Temp body: " + tempBody);
    log.info("Header Authorization: " + authorization);
    return ResponseEntity.ok("Scheduled Job POST");
  }

  @Operation(summary = "Check Scheduled Job")
  @GetMapping("/check")
  public ResponseEntity<String> checkScheduledJob(
  ) {
    ScheduledJobService.getConfigScheduledJobMap().values().forEach(scheduledFuture -> {
      log.info(String.valueOf(scheduledFuture.isCancelled()));
      log.info(String.valueOf(scheduledFuture.isDone()));
    });
    return ResponseEntity.ok("Checked Scheduled Job");
  }

  @Operation(summary = "Cancel Scheduled Job")
  @GetMapping("/cancel")
  public ResponseEntity<String> cancelScheduledJob(
  ) {
    ScheduledJobService.getConfigScheduledJobMap().values().forEach(scheduledFuture -> {
      log.info(String.valueOf(scheduledFuture.cancel(false)));
    });
    return ResponseEntity.ok("Cancel Scheduled Job");
  }
}
