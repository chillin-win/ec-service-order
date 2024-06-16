package com.winnie.ecserviceorder.controller;

import com.winnie.ecserviceorder.controller.dto.DeliveryDto;
import com.winnie.ecserviceorder.usecase.CreateDeliveryWithRCallingRNOnDifferentProxyObjectUseCase;
import com.winnie.ecserviceorder.util.DeliveryMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

  @Autowired
  private CreateDeliveryWithRCallingRNOnDifferentProxyObjectUseCase createDeliveryWithRCallingRNOnDifferentProxyObjectUseCase;

  @Operation(summary = "Create Delivery with REQUIRED Transaction Calling Requires New on different Proxy Object")
  @PostMapping("required-calling-requiresNew-on-differentProxy")
  public ResponseEntity<DeliveryDto> createWithRequiredCallingRequiresNewOnDifferentProxy(
      @RequestHeader String user,
      @RequestHeader Integer errorCode,
      @RequestBody DeliveryDto deliveryDto
  ) {
    return ResponseEntity.ok(
        DeliveryMapper.toDto(createDeliveryWithRCallingRNOnDifferentProxyObjectUseCase.create(
            deliveryDto, user, errorCode)));
  }

  @Operation(summary = "Create Delivery with REQUIRED Transaction Calling Requires New on different Proxy Object with Exception Handler on Inner Transaction")
  @PostMapping("required-calling-requiresNew-on-differentProxy-with-innerExceptionHandler")
  public ResponseEntity<DeliveryDto> createWithExceptionHandlerOnInnerTransaction(
      @RequestHeader String user,
      @RequestHeader Integer errorCode,
      @RequestBody DeliveryDto deliveryDto
  ) {
    return ResponseEntity.ok(
        DeliveryMapper.toDto(createDeliveryWithRCallingRNOnDifferentProxyObjectUseCase.createWithExceptionHandlerOnInnerTransaction(
            deliveryDto, user, errorCode)));
  }
}
