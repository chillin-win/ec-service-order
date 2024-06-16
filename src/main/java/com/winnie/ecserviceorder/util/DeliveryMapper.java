package com.winnie.ecserviceorder.util;

import com.winnie.ecserviceorder.controller.dto.DeliveryDto;
import com.winnie.ecserviceorder.entity.Delivery;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Delivery Mapper
 */
public class DeliveryMapper {

  public static Delivery toModel(DeliveryDto dto) {
    Delivery model = new Delivery();
    copyProperties(dto, model);

    return model;
  }

  public static DeliveryDto toDto(Delivery model) {
    DeliveryDto dto = new DeliveryDto();
    copyProperties(model, dto);

    return dto;
  }

}
