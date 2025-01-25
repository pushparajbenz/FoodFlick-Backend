package com.pn.food_cart_management.controller;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pn.food_cart_management.entity.Order;
import com.pn.food_cart_management.service.OrderService;
import com.pn.food_cart_management.utils.Revenue;

@RequestMapping("order-rest")
@RestController
public class OrderController {

	@Autowired
	OrderService orderservice;

	private static final Logger logger = LogManager.getLogger(OrderController.class);
	

	@PostMapping("/user/addOrder/{userId}")
	public void addOrder(@RequestParam(required = true) String address, @PathVariable long userId)
			throws MissingServletRequestParameterException {

		logger.info("In Add Order Endpoint");
		if (address.trim().isEmpty()) {
			logger.info("{}", address);
			throw new MissingServletRequestParameterException("address", "address cannot be empty string");
		}
		orderservice.addOrder(userId, address);
	}

	@GetMapping("/user/getUserOrder/{id}")
	public ResponseEntity<List<Order>> getByUser(@PathVariable long id) {
		logger.info("Fetch all User Order By ID Endpoint");
		List<Order> order = orderservice.getAllOrderOfUser(id);
		return new ResponseEntity<List<Order>>(order, HttpStatus.OK);
	}

	@GetMapping("/user/getOrder/{id}")
	public ResponseEntity<Order> getByOrder(@PathVariable long id) {
		logger.info("Fetch Order By ID Endpoint");

		return new ResponseEntity<Order>(orderservice.getOrderByOrderId(id), HttpStatus.OK);
	}


	@GetMapping("/admin/getRevenueMonthly")
	public Revenue getMonthlyRevenue(@RequestParam("fromdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
			@RequestParam("todate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2) {
		logger.info("In Monthly Revenue Endpoint");

		return orderservice.getRevenueMonthly(date1, date2);
	}

	@GetMapping("/admin/getdailyRevenue")
	public Revenue getRevenue(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
		logger.info("In Daily Revenue Endpoint");

		return orderservice.getRevenue(date);
	}

}
























































































































//
//@GetMapping("/getall")
//public ResponseEntity<List<Order>> getAllOrder() {
//	logger.info("Inside All Order");
//
//	return new ResponseEntity<List<Order>>(orderservice.getAllOrders(), HttpStatus.OK);
//}
//
//
