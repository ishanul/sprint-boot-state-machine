package com.ishan.statemachine.demo.order;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{id}")
    public Order getOrder(@PathVariable("id") Long id) {
		Order order = orderService.byId(id);
		return order;
	}
    
	@PostMapping("/")
    public Order createOrder() {
		Order order = orderService.create(new Date());
		return order;
	}
	
	@PostMapping("/pay/{id}")
    public Order payOrder(@PathVariable("id") Long id) {
		Order order = orderService.byId(id);
		StateMachine<OrderStates, OrderEvents> paymentStateMachine = orderService.pay(order.getId(), UUID.randomUUID().toString());
		log.info("after calling pay(): " + paymentStateMachine.getState().getId().name());
		log.info("order: " + orderService.byId(order.getId()));
		return order;
	}
	
	@PostMapping("/fulfill/{id}")
    public Order fulfillOrder(@PathVariable("id") Long id) {
		Order order = orderService.byId(id);
		
		StateMachine<OrderStates, OrderEvents> fulfilledStateMachine = orderService.fulfill(order.getId());
		log.info("after calling fulfill(): " + fulfilledStateMachine.getState().getId().name());
		log.info("order: " + orderService.byId(order.getId()));
		return order;
	}
	
}
