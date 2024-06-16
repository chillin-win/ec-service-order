package com.winnie.ecserviceorder.usecase;

import com.winnie.ecserviceorder.controller.dto.OrderDto;
import com.winnie.ecserviceorder.entity.Order;
import com.winnie.ecserviceorder.service.OrderService;
import com.winnie.ecserviceorder.util.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateWithRequiredCallingRequiresNewAndThrowingAfterT2UseCase {

    @Autowired
    private OrderService orderService;

    public Order create(OrderDto orderDto, String user, Integer errorCode, Integer errorCode2) {
        Order model = OrderMapper.toModel(orderDto);
        model.setId(UUID.randomUUID().toString());
        model.setCreatedBy(user);
        return orderService.createWithRequiredCallingRequiresNewAndThrowingAfterT2(model, errorCode, errorCode2);
    }

}
