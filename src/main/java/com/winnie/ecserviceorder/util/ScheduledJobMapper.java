package com.winnie.ecserviceorder.util;

import com.winnie.ecserviceorder.controller.dto.ScheduledJobDto;
import com.winnie.ecserviceorder.entity.ScheduledJob;
import org.springframework.beans.BeanUtils;

/**
 * ScheduledJob Mapper
 */
public class ScheduledJobMapper {

  public static ScheduledJob toModel(ScheduledJobDto dto) {
    ScheduledJob model = new ScheduledJob();
    BeanUtils.copyProperties(dto, model);

    return model;
  }

  public static ScheduledJobDto toDto(ScheduledJob model) {
    ScheduledJobDto dto = new ScheduledJobDto();
    BeanUtils.copyProperties(model, dto);

    return dto;
  }
}
