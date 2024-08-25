package com.winnie.ecserviceorder.usecase;

import com.winnie.ecserviceorder.controller.dto.OrderDto;
import com.winnie.ecserviceorder.entity.Order;
import com.winnie.ecserviceorder.service.OrderService;
import com.winnie.ecserviceorder.util.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class UpdateOrderUseCase {

    @Autowired
    private OrderService orderService;

    public Order updateOrderWithCreationQuery(Integer orderId, OrderDto orderDto, String user, Integer errorCode) {
        Order updateModel = OrderMapper.toModel(orderDto);
        return orderService.updateOrderWithCreationQuery(orderId, updateModel, errorCode);
    }

    public Order updateOrderWithNativeQuery(Integer orderId, OrderDto orderDto, String user, Integer errorCode) throws InterruptedException {
        Order updateModel = OrderMapper.toModel(orderDto);
        orderService.updateOrderWithNativeQuery(orderId, updateModel, errorCode);
        Order after = orderService.findById(orderId);
        return after;
    }

}
