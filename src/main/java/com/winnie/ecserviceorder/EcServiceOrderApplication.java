package com.winnie.ecserviceorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EcServiceOrderApplication {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(EcServiceOrderApplication.class, args);
  }

}