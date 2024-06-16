package com.winnie.ecserviceorder.config;

import com.winnie.ecserviceorder.entity.Order;
import com.winnie.ecserviceorder.service.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class EventListener {

  @Autowired
  private EventBus eventBus;

  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT, value = Order.class)
  public void handleBeforeCommit(Order order) throws InterruptedException {
    eventBus.addOrder(order);
    log.info("Payload: {}", order);
    log.info("Waiting to persist data");
    TimeUnit.SECONDS.sleep(10);
    log.info("Data persisted");
  }
}
