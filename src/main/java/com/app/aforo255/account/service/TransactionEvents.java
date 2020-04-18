package com.app.aforo255.account.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.aforo255.account.kafka.consumer.listener.TransactionEventsConsumer;
import com.app.aforo255.account.model.entity.Account;
import com.app.aforo255.account.model.entity.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TransactionEvents {
	
	private Logger log = LoggerFactory.getLogger(TransactionEvents.class);
	@Autowired
	private IAccountService accountRepository;
	@Autowired
	ObjectMapper objectMapper;
	
	public void processTransactionEvent(ConsumerRecord<Integer, String> consumerRecord) throws JsonMappingException, JsonProcessingException {
		double newAmount = 0;
		Account objAccount = new Account();
		Transaction transactionEvent = objectMapper.readValue(consumerRecord.value(), Transaction.class);

		log.info("transactionEvent: {}", transactionEvent.getAccountId());
		objAccount = accountRepository.findById(transactionEvent.getAccountId());
		log.info("get amount: {}", objAccount.getTotalAmount());
		
		switch(transactionEvent.getType()) {
			case "deposito":
				newAmount = objAccount.getTotalAmount() + transactionEvent.getAmount();
				break;
			case "retiro":
				newAmount = objAccount.getTotalAmount() - transactionEvent.getAmount();
				break;
			default:
				log.info("Invalid library Event type");
				break;
		}
		
		log.info("set new amount: {}", newAmount);
		objAccount.setTotalAmount(newAmount);
		accountRepository.save(objAccount);
	}
}
