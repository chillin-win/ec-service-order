package com.winnie.ecserviceorder.util;

import com.winnie.ecserviceorder.controller.dto.OrderDto;
import com.winnie.ecserviceorder.entity.Order;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Order mapper.
 */
public class OrderMapper {

    public static Order toModel(OrderDto dto) {
        Order model = new Order();
        if (!Objects.isNull(dto)) BeanUtils.copyProperties(dto, model);

        return model;
    }

    public static OrderDto toDto(Order model) {
        OrderDto dto = new OrderDto();
        if (!Objects.isNull(model)) BeanUtils.copyProperties(model, dto);

        return dto;
    }

    public static List<OrderDto> toDtos(List<Order> models) {
        List<OrderDto> dtos = new ArrayList<>();
        models.forEach(order -> dtos.add(toDto(order)));

        return dtos;
    }
}
