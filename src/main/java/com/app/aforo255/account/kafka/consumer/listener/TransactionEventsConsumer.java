package com.app.aforo255.account.kafka.consumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.app.aforo255.account.service.TransactionEvents;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class TransactionEventsConsumer {
	
	private Logger log = LoggerFactory.getLogger(TransactionEventsConsumer.class);
	@Autowired
	private TransactionEvents transactionEvents;
	
	@KafkaListener(topics = {"transaction-events"})
	public void onMessage(ConsumerRecord<Integer, String> consumerRecord) throws JsonMappingException, JsonProcessingException {
		log.info("ConsumerRecord account: {}", consumerRecord);
		transactionEvents.processTransactionEvent(consumerRecord);
	}
}
