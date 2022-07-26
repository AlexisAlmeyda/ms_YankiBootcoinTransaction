package com.bootcamp.yankibootcoin.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.bootcamp.yankibootcoin.events.SendMoneyEvent;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
  
  @Value("${spring.kafka.bootstrap-servers}")
  private String boostrapServer;
  
  @Bean
  public ConsumerFactory<String, SendMoneyEvent<?>> consumerFactory() {
    Map<String, String> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServer);
    props.put(JsonSerializer.TYPE_MAPPINGS, "com.bootcamp.yankibootcoin:com.bootcamp.yankibootcoin.events.SendMoneyEvent");
    final JsonDeserializer<SendMoneyEvent<?>> jsonDeserializer = new JsonDeserializer<>();
    return new DefaultKafkaConsumerFactory(props, new StringDeserializer(), jsonDeserializer);
  }
  
  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, SendMoneyEvent<?>> kafkaListenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, SendMoneyEvent<?>> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

}
