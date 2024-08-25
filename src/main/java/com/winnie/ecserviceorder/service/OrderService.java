package com.winnie.ecserviceorder.service;

import com.winnie.ecserviceorder.entity.Order;
import com.winnie.ecserviceorder.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@Slf4j
public class OrderService {

  public static Integer tempObjectCounter = 0;

  private OrderRepository orderRepository;
  private final EntityManager entityManager;
  private final JdbcTemplate jdbcTemplate;

  public OrderService(OrderRepository orderRepository, EntityManager entityManager, JdbcTemplate jdbcTemplate) {
    this.orderRepository = orderRepository;
    this.entityManager = entityManager;
    this.jdbcTemplate = jdbcTemplate;
  }

  public static Order getTempOrder() {
    Order tempOrder = new Order();
    tempOrder.setId(tempObjectCounter.toString());
    tempOrder.setName("Temp Order " + tempObjectCounter);

    return tempOrder;
  }

  public List<Order> findAll() {
    return orderRepository.findAll();
  }

  public Order create(Order order) {
    order.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
    return orderRepository.save(order);
  }

  @Transactional
  public Order createWithAnnotatedMethod(Order order, Integer errorCode) {
    order.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
    Order result = orderRepository.save(order);
    if (errorCode == 1) throw new RuntimeException("Creating with error code: 1");
    return result;
  }

  @Transactional
  public Order updateOrderWithCreationQuery(Integer orderId, Order updateModel, Integer errorCode) {
    Order existingOrder = orderRepository.findById(orderId).get();
    existingOrder.setName(updateModel.getName());

    orderRepository.save(existingOrder);

    Order afterSaving = orderRepository.findById(orderId).get();
//    Order afterSaving2 = orderRepository.findByName(updateModel.getName() + "/update");
    Order afterSaving3 = orderRepository.findByIdNative(orderId);
    return afterSaving;
  }

  @Transactional
  public Order updateOrderWithNativeQuery(Integer orderId, Order updateModel, Integer errorCode) throws InterruptedException {
    Order existingOrder = orderRepository.findById(orderId).get();
    existingOrder.setName(updateModel.getName());

    orderRepository.save(existingOrder);
//    orderRepository.saveAndFlush(existingOrder);

    Order afterSaving = orderRepository.findByNameWildCard(updateModel.getName());
    Thread.sleep(5000);
    return afterSaving;
  }

  @Transactional(readOnly = true)
  public Order createWithAnnotatedMethodAndReadOnly(Order order, Integer errorCode) {
    order.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
    Order result = orderRepository.save(order);
    if (errorCode == 1) throw new RuntimeException("Creating with error code: 1");
    return result;
  }

  public Order createWithNonAnnotatedCallingAnnotatedMethod(Order order, Integer errorCode) {
    this.create(OrderService.getTempOrder());
    tempObjectCounter++;

    log.info("Non-annotated method invoking annotated method");
    return createWithAnnotatedMethod(order, errorCode);
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Order createWithRequireNewMethod(Order order, Integer errorCode) {
    order.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
    Order result = orderRepository.save(order);
    if (errorCode == 1) throw new RuntimeException("Creating with error code: 1");
    return result;
  }

  @Transactional
  public Order createWithRequiredCallingRequiresNew(Order order, Integer errorCode) {
    order.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
    Order result = orderRepository.save(order);
    this.createWithRequireNewMethod(OrderService.getTempOrder(), errorCode);

    return result;
  }

  @Transactional
  public Order createWithRequiredCallingRequiresNewAndExceptionHandler(Order order, Integer errorCode) {
    order.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
    Order result = orderRepository.save(order);
    try {
      this.createWithRequireNewMethod(OrderService.getTempOrder(), errorCode);
    } catch (Exception ex) {
      log.error(ex.getMessage());
    }

    return result;
  }

  @Transactional
  public Order createWithRequiredCallingRequiresNewAndThrowingAfterT2(Order order, Integer errorCode1, Integer errorCode2) {
    order.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
    Order result = orderRepository.save(order);
    this.createWithRequireNewMethod(OrderService.getTempOrder(), errorCode1);
    if (errorCode2 == 1) throw new RuntimeException("Throwing Exception after running T2");

    return result;
  }

  @Transactional
  public Order createWithInnerSavePoint(Order order, Integer errorCode) {
    this.createWithSavePoint(0);

    order.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
    Order result = orderRepository.save(order);
    if (errorCode == 1) throw new RuntimeException("Creating with error code: 1");
    return result;
  }

  @Transactional(propagation = Propagation.NESTED)
  public Order createWithSavePoint(Integer errorCode) {
    Order data = OrderService.getTempOrder();
    data.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
    Order result = orderRepository.save(data);
    if (errorCode == 1) throw new RuntimeException("Creating with error code: 1");

    return result;
  }

  public Order findByName(String orderName) {
    return orderRepository.findByName(orderName);
  }

  @Transactional
  public Order findById(Integer orderId) {
    return orderRepository.findById(orderId).orElse(null);
  }

  public void removeTempOrder() {
    orderRepository.removeTempOrder();
  }

}
