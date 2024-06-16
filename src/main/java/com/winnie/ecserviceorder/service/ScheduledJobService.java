package com.winnie.ecserviceorder.service;

import com.winnie.ecserviceorder.entity.ScheduledJob;
import com.winnie.ecserviceorder.repository.ScheduledJobRepository;
import com.winnie.ecserviceorder.util.ScheduledTaskThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
@Slf4j
public class ScheduledJobService {

  private static Map<ScheduledJob, ScheduledFuture> configScheduledJobMap = new HashMap<>();
  @Autowired
  private TaskScheduler taskScheduler;
  @Autowired
  private ScheduledJobRepository scheduledJobRepository;

  public static Map<ScheduledJob, ScheduledFuture> getConfigScheduledJobMap() {
    return configScheduledJobMap;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void loadAndScheduleJob() {
    scheduledJobRepository.findAll().forEach(scheduledJob -> {
      ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(new ScheduledTaskThread(scheduledJob), new CronTrigger(scheduledJob.getCron()));
      log.info(String.valueOf(scheduledFuture.isCancelled()));
      log.info(String.valueOf(scheduledFuture.isDone()));
      configScheduledJobMap.put(scheduledJob, scheduledFuture);
    });
    log.info("Loaded and Scheduled all config job");
  }

  public ScheduledJob create(ScheduledJob scheduledJob) {
    ScheduledJob result = scheduledJobRepository.save(scheduledJob);
    ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(new ScheduledTaskThread(result), new CronTrigger(result.getCron()));
    configScheduledJobMap.put(result, scheduledFuture);

    return result;
  }

//  @PostConstruct
//  public void loadAndScheduleJob() {
//    scheduledJobRepository.findAll().forEach(scheduledJob -> {
//      ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(new ScheduledTaskThread(scheduledJob), new CronTrigger(scheduledJob.getCron()));
//      configScheduledJobMap.put(scheduledJob, scheduledFuture);
//    });
//    log.info("Loaded and Scheduled all config job");
//  }

//  public void setUpRestTemplate() {
//    restTemplate = new RestTemplate();
//    // Add more config if it requires
////    restTemplate.getInterceptors().add((request, body, execution) -> {});
//  }
}
