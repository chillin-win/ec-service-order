package com.winnie.ecserviceorder.util;

import com.winnie.ecserviceorder.controller.dto.OrderDto;
import com.winnie.ecserviceorder.entity.Order;
import org.springframework.beans.BeanUtils;

/**
 * Order mapper.
 */
public class OrderMapper {

  public static Order toModel(OrderDto dto) {
    Order model = new Order();
    BeanUtils.copyProperties(dto, model);

    return model;
  }

  public static OrderDto toDto(Order model) {
    OrderDto dto = new OrderDto();
    BeanUtils.copyProperties(model, dto);

    return dto;
  }
}
