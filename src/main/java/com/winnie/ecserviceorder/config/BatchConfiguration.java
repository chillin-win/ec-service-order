package com.winnie.ecserviceorder.config;

import com.winnie.ecserviceorder.entity.Order;
import com.winnie.ecserviceorder.repository.OrderRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Autowired
  private OrderRepository orderRepository;

  /**
   * Job with flow, has step.
   */

  @Bean
  public ItemReader<Order> reader() {
    return new FlatFileItemReaderBuilder<Order>()
        .name("orderItemReader")
        .resource(new ClassPathResource("Order.csv"))
        .delimited()
        .names(new String[]{"id", "name", "createdBy"})
        .fieldSetMapper(new BeanWrapperFieldSetMapper<Order>() {{
          setTargetType(Order.class);
        }})
        .build();
  }

  @Bean
  public ItemProcessor<Order, Order> processor() {
    return new ItemProcessor<Order, Order>() {

      @Override
      public Order process(final Order order) throws Exception {
        return order;
      }
    };
  }

  @Bean
  @StepScope
  public ItemWriter<Order> writer(@Value("#{jobParameters}") Map<String, Object> jobParameters) {
    System.out.println(jobParameters);
    RepositoryItemWriter<Order> writer = new RepositoryItemWriter<>();
    writer.setRepository(orderRepository);
    writer.setMethodName("save");
    return writer;
  }

  @Bean
  public Step step1(ItemWriter<Order> itemWriter) {
    return stepBuilderFactory.get("step1")
        .<Order, Order>chunk(10)
        .reader(reader())
        .processor(processor())
        .writer(itemWriter)
        .build();
  }

  @Bean
  public Job flowStepJob(@Qualifier("step1") Step step1) {
    return jobBuilderFactory.get("flowStepJob")
        .incrementer(new RunIdIncrementer())
        .flow(step1)
        .end()
        .build();
  }

  /**
   * Job without read, process, write step.
   */
  @Bean
  public Job taskLetJob() {
    return jobBuilderFactory.get("taskLetJob")
        .start(taskletStep())
        .build();
  }

  @Bean
  public Step taskletStep() {
    return stepBuilderFactory.get("taskletStep")
        .tasklet(tasklet())
        .build();
  }

  @Bean
  public Tasklet tasklet() {
    return (contribution, chunkContext) -> {
      System.out.println("Finish tasklet job");
      return RepeatStatus.FINISHED;
    };
  }
}
