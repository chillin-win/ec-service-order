package com.winnie.ecserviceorder.service;

import com.winnie.ecserviceorder.entity.Delivery;
import com.winnie.ecserviceorder.repository.DeliveryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Transactional
@Slf4j
public class DeliveryService {

  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired
  private AdvancedOrderService advancedOrderService;

  public Delivery createWithRCallingRNOnDifferentProxyObject(Delivery delivery, Integer errorCode) {
    delivery.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
    Delivery result = deliveryRepository.save(delivery);
    advancedOrderService.createWithAnnotatedClass(OrderService.getTempOrder(), errorCode);

    return result;
  }

  public Delivery createWithRCallingRNOnDifferentProxyObjectAndExceptionHandler(Delivery delivery, Integer errorCode) {
    delivery.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
    Delivery result = deliveryRepository.save(delivery);
    try {
      advancedOrderService.createWithAnnotatedClass(OrderService.getTempOrder(), errorCode);
    } catch (Exception ex) {
      log.debug(ex.getMessage());
    }

    return result;
  }

}
