package com.winnie.ecserviceorder.usecase;

import com.winnie.ecserviceorder.controller.dto.OrderDto;
import com.winnie.ecserviceorder.entity.Order;
import com.winnie.ecserviceorder.service.OrderService;
import com.winnie.ecserviceorder.util.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Create order use case.
 */
@Component
@Slf4j
public class CreateOrderUseCase {

  @Autowired
  private ApplicationEventPublisher eventPublisher;

  @Autowired
  private OrderService orderService;

  @Async
  @Transactional(isolation = Isolation.SERIALIZABLE)
  public void createLater(OrderDto orderDto, String user) {
    log.info("Start creating");
    Order model = OrderMapper.toModel(orderDto);
    model.setId(UUID.randomUUID().toString());
    model.setCreatedBy(user);
    eventPublisher.publishEvent(model);
    orderService.create(model);
  }
  public void removeTempOrder() {
    orderService.removeTempOrder();
  }
}
