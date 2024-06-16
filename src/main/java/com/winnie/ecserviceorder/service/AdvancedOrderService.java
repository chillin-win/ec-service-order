package com.winnie.ecserviceorder.service;

import com.winnie.ecserviceorder.entity.Order;
import com.winnie.ecserviceorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class AdvancedOrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Transactional(propagation = Propagation.REQUIRED)
  public Order createWithAnnotatedClass(Order order, Integer errorCode) {
    order.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
    Order result = orderRepository.save(order);
    if (errorCode == 1) throw new RuntimeException("Creating with error code: 1");
    return result;
  }

}
