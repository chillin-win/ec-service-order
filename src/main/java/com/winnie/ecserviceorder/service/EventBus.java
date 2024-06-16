package com.winnie.ecserviceorder.service;

import com.winnie.ecserviceorder.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class EventBus {

  private final List<Order> orders = new ArrayList<>();

  public void addOrder(Order order) {
    orders.add(order);
  }

  public Order findFirst() {
    Order order = null;
    int count = 300;
    while (count > 0) {
      order = orders.stream().findFirst().orElse(null);
      if (Objects.nonNull(order)) {
        orders.remove(order);
        return order;
      }
      try {
        TimeUnit.MILLISECONDS.sleep(10);
      } catch (InterruptedException ex) {
        log.error(ex.getMessage(), ex);
      }
      --count;
    }
    return order;
  }
}
