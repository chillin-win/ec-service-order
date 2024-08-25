package com.winnie.ecserviceorder.usecase;

import com.winnie.ecserviceorder.controller.dto.ScheduledJobDto;
import com.winnie.ecserviceorder.entity.ScheduledJob;
import com.winnie.ecserviceorder.service.ScheduledJobService;
import com.winnie.ecserviceorder.util.ScheduledJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateScheduledJobUseCase {

    @Autowired
    private ScheduledJobService scheduledJobService;

    public ScheduledJob create(ScheduledJobDto scheduledJobDto) {
        ScheduledJob model = ScheduledJobMapper.toModel(scheduledJobDto);
        return scheduledJobService.create(model);
    }
}
