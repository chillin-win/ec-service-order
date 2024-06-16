package com.winnie.ecserviceorder;

import com.winnie.ecserviceorder.controller.OrderController;
import com.winnie.ecserviceorder.controller.dto.OrderDto;
import com.winnie.ecserviceorder.entity.Order;
import com.winnie.ecserviceorder.service.EventBus;
import com.winnie.ecserviceorder.usecase.CreateOrderUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

  @Mock
  private EventBus eventBus;
  @Mock
  private CreateOrderUseCase createOrderUseCase;

  @InjectMocks
  private OrderController orderController;

  private OrderDto orderDto;
  private Order order;

  @BeforeEach
  public void setUp() {
    orderDto = new OrderDto();
    orderDto.setName("Later order 1");

    order = new Order();
    order.setId("id");
    order.setName("Later order 1");
  }

  @Test
  public void test() {
    when(eventBus.findFirst()).thenReturn(order);

    ResponseEntity<OrderDto> result = orderController.createOrder("username", orderDto);

    assertEquals(result.getStatusCode(), HttpStatus.OK);
    assertEquals(result.getBody().getId(), order.getId());
  }
}
