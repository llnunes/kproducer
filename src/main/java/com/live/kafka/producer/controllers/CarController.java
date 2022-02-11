package com.live.kafka.producer.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.live.kafka.producer.dtos.CarDTO;
import com.live.kafka.producer.services.CarProducerService;

@RestController
@RequestMapping(value = "/cars")
public class CarController {
	
	@Autowired
	private CarProducerService service;
	
	@PostMapping
	public ResponseEntity<CarDTO> create(@RequestBody CarDTO carDTO) {
		carDTO.setId(UUID.randomUUID().toString());
		service.send(carDTO);		

		return ResponseEntity.status(HttpStatus.CREATED).body(carDTO);
	}	

	@GetMapping
	public ResponseEntity<?> listar(){
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
