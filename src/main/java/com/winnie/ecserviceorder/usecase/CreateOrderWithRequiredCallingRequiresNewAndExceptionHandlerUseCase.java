package com.winnie.ecserviceorder.usecase;

import com.winnie.ecserviceorder.controller.dto.OrderDto;
import com.winnie.ecserviceorder.entity.Order;
import com.winnie.ecserviceorder.service.OrderService;
import com.winnie.ecserviceorder.util.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderWithRequiredCallingRequiresNewAndExceptionHandlerUseCase {

    @Autowired
    private OrderService orderService;

    public Order create(OrderDto orderDto, String user, Integer errorCode) {
        Order model = OrderMapper.toModel(orderDto);
        model.setCreatedBy(user);
        return orderService.createWithRequiredCallingRequiresNewAndExceptionHandler(model, errorCode);
    }
}
