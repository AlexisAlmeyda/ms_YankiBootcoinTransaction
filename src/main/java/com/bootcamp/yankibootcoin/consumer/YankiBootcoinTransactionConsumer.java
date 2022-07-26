package com.bootcamp.yankibootcoin.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.bootcamp.yankibootcoin.events.SendMoneyEvent;
import com.bootcamp.yankibootcoin.events.WalletBootcoinSendMoneyEvent;

@Component
public class YankiBootcoinTransactionConsumer {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(YankiBootcoinTransactionConsumer.class);
  
  @KafkaListener(topics = "${topic.wallet.name}", containerFactory = "kafkaListenerContainerFactory", groupId = "grupo1")
  public void consumer(SendMoneyEvent<?> sendMoneyEvent) {
    if (sendMoneyEvent.getClass().isAssignableFrom(WalletBootcoinSendMoneyEvent.class)) {
      WalletBootcoinSendMoneyEvent walletBootcoinSendMoneyEvent = (WalletBootcoinSendMoneyEvent) sendMoneyEvent;
      LOGGER.info("The consumer receives transaction data with Id={}, data={}", walletBootcoinSendMoneyEvent.getId(),
          walletBootcoinSendMoneyEvent.getData().toString());
    }
  }

}
