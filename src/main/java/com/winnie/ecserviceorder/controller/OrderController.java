package com.winnie.ecserviceorder.controller;

import com.winnie.ecserviceorder.controller.dto.OrderDto;
import com.winnie.ecserviceorder.service.EventBus;
import com.winnie.ecserviceorder.usecase.*;
import com.winnie.ecserviceorder.util.OrderMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    public static final String X_JWT_FIRST_NAME_HEADER = "X-jwt-first-name";
    public static final String X_JWT_LAST_NAME_HEADER = "X-jwt-last-name";
    private static final String X_JWT_USER_HEADER = "X-jwt-user";

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier("flowStepJob")
    private Job flowStepJob;

    @Autowired
    @Qualifier("taskLetJob")
    private Job taskLetJob;

    @Autowired
    private EventBus eventBus;

    @Autowired
    private CreateOrderUseCase createOrderUseCase;

    @Autowired
    private UpdateOrderUseCase updateOrderUseCase;

    @Autowired
    private CreateOrderWithAnnotatedMethodUseCase createOrderWithAnnotatedMethoduseCase;

    @Autowired
    private CreateOrderWithNonAnnotatedCallingAnnotatedUseCase createOrderWithNonAnnotatedCallingAnnotateduseCase;

    @Autowired
    private FindOrdersUseCase findOrdersUseCase;

    @Autowired
    private FindOrderByNameUseCase findOrderByNameUseCase;

    @Autowired
    private CreateOrderWithRequiredCallingRequiresNewUseCase createOrderWithRequiredCallingRequiresNewUseCase;

    @Autowired
    private CreateOrderWithInnerSavePointUseCase createOrderWithInnerSavePointUseCase;

    @Autowired
    private CreateOrderWithRequiredCallingRequiresNewAndExceptionHandlerUseCase createOrderWithRequiredCallingRequiresNewAndExceptionHandlerUseCase;

    @Autowired
    private CreateWithRequiredCallingRequiresNewAndThrowingAfterT2UseCase createWithRequiredCallingRequiresNewAndThrowingAfterT2UseCase;

    @Operation(summary = "Find all")
    @GetMapping("")
    public ResponseEntity<List<OrderDto>> findAll() {
        return ResponseEntity.ok(OrderMapper.toDtos(findOrdersUseCase.findAll()));
    }

    @Operation(summary = "Create order with 10 seconds delay for persisting data by Transactional Event Listener")
    @PostMapping("/by-transactional-event-listener")
    public ResponseEntity<OrderDto> createOrder(@RequestHeader String user, @RequestBody OrderDto orderDto) {
        createOrderUseCase.createLater(orderDto, user);
        return ResponseEntity.ok(OrderMapper.toDto(eventBus.findFirst()));
    }

    @Operation(summary = "Find By Name")
    @GetMapping("/name/{orderName}")
    public ResponseEntity<OrderDto> findByName(@PathVariable String orderName) {
        return ResponseEntity.ok(OrderMapper.toDto(findOrderByNameUseCase.find(orderName)));
    }

    @Operation(summary = "Find By Id")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> findById(@PathVariable Integer orderId) {
        return ResponseEntity.ok(OrderMapper.toDto(findOrderByNameUseCase.findById(orderId)));
    }

    @Operation(summary = "Create order with annotated method")
    @PostMapping("/annotatedMethod")
    public ResponseEntity<OrderDto> createOrderWithAnnotatedMethod(@RequestHeader String user, @RequestHeader Integer errorCode, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(OrderMapper.toDto(createOrderWithAnnotatedMethoduseCase.create(orderDto, user, errorCode)));
    }

    @Operation(summary = "Update order with annotated method")
    @PatchMapping("/annotatedMethod/creationQuery/{orderId}")
    public ResponseEntity<OrderDto> updateOrderWithCreationQuery(@RequestHeader String user, @RequestHeader Integer errorCode, @PathVariable Integer orderId, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(OrderMapper.toDto(updateOrderUseCase.updateOrderWithCreationQuery(orderId, orderDto, user, errorCode)));
    }

    @Operation(summary = "Update order with annotated method")
    @PatchMapping("/annotatedMethod/nativeQuery/{orderId}")
    public ResponseEntity<OrderDto> updateOrderWithNativeQuery(@RequestHeader String user, @RequestHeader Integer errorCode, @PathVariable Integer orderId, @RequestBody OrderDto orderDto) throws InterruptedException {
        return ResponseEntity.ok(OrderMapper.toDto(updateOrderUseCase.updateOrderWithNativeQuery(orderId, orderDto, user, errorCode)));
    }

    @Operation(summary = "Create order with annotated method and read only")
    @PostMapping("/annotatedMethod-readOnly")
    public ResponseEntity<OrderDto> createOrderWithAnnotatedMethodAndReadOnly(@RequestHeader String user, @RequestHeader Integer errorCode, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(OrderMapper.toDto(createOrderWithAnnotatedMethoduseCase.createWithReadOnly(orderDto, user, errorCode)));
    }

    @Operation(summary = "Create order with non-annotated method calling annotated method")
    @PostMapping("/nonAnnotated-calling-annotatedMethod")
    public ResponseEntity<OrderDto> createWithNonAnnotatedCallingAnnotated(@RequestHeader String user, @RequestHeader Integer errorCode, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(OrderMapper.toDto(createOrderWithNonAnnotatedCallingAnnotateduseCase.create(orderDto, user, errorCode)));
    }

    @Operation(summary = "Create order with required calling requires-new method")
    @PostMapping("/required-calling-requiresNew")
    public ResponseEntity<OrderDto> createWithRequiredCallingRequiresNew(@RequestHeader String user, @RequestHeader Integer errorCode, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(OrderMapper.toDto(createOrderWithRequiredCallingRequiresNewUseCase.create(orderDto, user, errorCode)));
    }

    @Operation(summary = "Create Order With Required Calling Requires New but there is Exception Handler in T1 for T2")
    @PostMapping("/required-calling-requiresNew-exceptionHandler")
    public ResponseEntity<OrderDto> createWithRequiredCallingRequiresNewAndExceptionHandler(@RequestHeader String user, @RequestHeader Integer errorCode, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(OrderMapper.toDto(createOrderWithRequiredCallingRequiresNewAndExceptionHandlerUseCase.create(orderDto, user, errorCode)));
    }

    @Operation(summary = "Create Order With Required Calling Requires New but there is Exception Handler in T1 for T2")
    @PostMapping("/required-calling-requiresNew-throwing-after-T2")
    public ResponseEntity<OrderDto> createWithRequiredCallingRequiresNewAndThrowingAfterT2(@RequestHeader String user, @RequestHeader Integer errorCode, @RequestHeader Integer errorCode2, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(OrderMapper.toDto(createWithRequiredCallingRequiresNewAndThrowingAfterT2UseCase.create(orderDto, user, errorCode, errorCode2)));
    }

    @Operation(summary = "Create Order with Inner Save Point")
    @PostMapping("/create-with-inner-savePoint")
    public ResponseEntity<OrderDto> createWithInnerSavePoint(@RequestHeader String user, @RequestHeader Integer errorCode, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(OrderMapper.toDto(createOrderWithInnerSavePointUseCase.create(orderDto, user, errorCode)));
    }

    @Operation(summary = "Remove temp Order")
    @GetMapping("/remove-temp")
    public ResponseEntity<Object> removeTempOrder() {
        createOrderUseCase.removeTempOrder();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Run batch manually")
    @PostMapping("/batch")
    public ResponseEntity<Void> batchJob(
            @RequestHeader String jobType,
            @RequestHeader(value = X_JWT_USER_HEADER, required = false) String username,
            @RequestHeader(value = X_JWT_FIRST_NAME_HEADER, required = false) String firstname,
            @RequestHeader(value = X_JWT_LAST_NAME_HEADER, required = false) String lastname) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addString("randomId", UUID.randomUUID().toString()).toJobParameters();
        Job job = "flowStepJob".equals(jobType) ? flowStepJob : taskLetJob;
        JobExecution execution = jobLauncher.run(job, jobParameters);
        log.info("BATCH CONTROLLER STATUS :: " + execution.getStatus());

        return ResponseEntity.ok().build();
    }

}
