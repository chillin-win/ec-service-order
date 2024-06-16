package com.winnie.ecserviceorder.usecase;

import com.winnie.ecserviceorder.entity.Order;
import com.winnie.ecserviceorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindOrderByNameUseCase {

  @Autowired
  private OrderService orderService;

  public Order find(String orderName) {
    return orderService.findByName(orderName);
  }

  public Order findById(String orderId) {
    return orderService.findById(orderId);
  }
}
