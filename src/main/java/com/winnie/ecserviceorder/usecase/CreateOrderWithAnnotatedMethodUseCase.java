package com.winnie.ecserviceorder.usecase;

import com.winnie.ecserviceorder.controller.dto.OrderDto;
import com.winnie.ecserviceorder.entity.Order;
import com.winnie.ecserviceorder.service.OrderService;
import com.winnie.ecserviceorder.util.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CreateOrderWithAnnotatedMethodUseCase {

  @Autowired
  private OrderService orderService;

  public Order create(OrderDto orderDto, String user, Integer errorCode) {
    Order model = OrderMapper.toModel(orderDto);
    model.setId(UUID.randomUUID().toString());
    model.setCreatedBy(user);
    return orderService.createWithAnnotatedMethod(model, errorCode);
  }

  public Order createWithReadOnly(OrderDto orderDto, String user, Integer errorCode) {
    Order model = OrderMapper.toModel(orderDto);
    model.setId(UUID.randomUUID().toString());
    model.setCreatedBy(user);
    return orderService.createWithAnnotatedMethodAndReadOnly(model, errorCode);
  }
}
