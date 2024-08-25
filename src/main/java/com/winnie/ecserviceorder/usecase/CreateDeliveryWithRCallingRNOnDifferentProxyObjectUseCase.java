package com.winnie.ecserviceorder.usecase;

import com.winnie.ecserviceorder.controller.dto.DeliveryDto;
import com.winnie.ecserviceorder.entity.Delivery;
import com.winnie.ecserviceorder.service.DeliveryService;
import com.winnie.ecserviceorder.util.DeliveryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateDeliveryWithRCallingRNOnDifferentProxyObjectUseCase {

    @Autowired
    private DeliveryService deliveryService;

    public Delivery create(DeliveryDto deliveryDto, String user, Integer errorCode) {
        Delivery model = DeliveryMapper.toModel(deliveryDto);
        model.setCreatedBy(user);

        return deliveryService.createWithRCallingRNOnDifferentProxyObject(model, errorCode);
    }

    public Delivery createWithExceptionHandlerOnInnerTransaction(DeliveryDto deliveryDto, String user, Integer errorCode) {
        Delivery model = DeliveryMapper.toModel(deliveryDto);

        model.setCreatedBy(user);

        return deliveryService.createWithRCallingRNOnDifferentProxyObjectAndExceptionHandler(model, errorCode);
    }
}
