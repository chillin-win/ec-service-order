package com.winnie.ecserviceorder.config;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.winnie.ecserviceorder.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration("Prestart-Configuration")
@EnableWebMvc
@Slf4j
public class WebConfig implements WebMvcConfigurer {

  public WebConfig(OrderRepository orderRepository) {
    orderRepository.removeTempOrder();
    log.info("Remove Temp Orders Before Application Start Successfully!!");
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    // For Swagger
    StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
    messageConverter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.ALL));
    converters.add(messageConverter);

    // Leveraging From Old Repo
    converters.add(new MappingJackson2HttpMessageConverter());
    for (HttpMessageConverter<?> converter : converters) {
      if (converter instanceof MappingJackson2HttpMessageConverter) {
        ObjectMapper mapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
      }
    }
  }

}

