package com.live.kafka.producer.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.live.kafka.producer.dtos.CarDTO;

@Service
public class CarProducerService {
	
	private static final Logger log = LoggerFactory.getLogger(CarProducerService.class);
	
	@Value("${topic.name}")
	private String topic;
	
	@Autowired
	private KafkaTemplate<String, CarDTO> kafkaTemplate;
	
	public void send(CarDTO carDTO) {
		kafkaTemplate.send(topic, carDTO).addCallback(
				success -> log.info("Mensage send: " + success.getProducerRecord().value()),
				failure -> log.info("Mensage failure: " + failure.getMessage())
		);
	}

}