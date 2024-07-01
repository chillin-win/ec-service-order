package com.winnie.ecserviceorder.usecase;

import com.winnie.ecserviceorder.entity.Order;
import com.winnie.ecserviceorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindOrdersUseCase {

    @Autowired
    private OrderService orderService;

    public List<Order> findAll() {
        return orderService.findAll();
    }

}
