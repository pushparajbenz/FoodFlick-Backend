package com.pn.food_cart_management.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pn.food_cart_management.entity.Cart;
import com.pn.food_cart_management.entity.Order;
import com.pn.food_cart_management.entity.UserInfo;
import com.pn.food_cart_management.exception.OrderNotFoundException;
import com.pn.food_cart_management.exception.UserNotExistException;
import com.pn.food_cart_management.repository.OrderRepository;
import com.pn.food_cart_management.repository.UserInfoRepository;
import com.pn.food_cart_management.utils.OrderDetails;
import com.pn.food_cart_management.utils.Revenue;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	CartService cartService;


	@Override
	@Transactional
	public void addOrder(long userId, String Address) {
		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);
		if (!userInfo.isPresent()) {
			throw new UserNotExistException("User not found");
		}
		
		List<Cart> cart = cartService.getCart(userId);

		Timestamp today = Timestamp.from(Instant.now());
		Order order = new Order();
		order.setUserId(userId);
		order.setCalendarDate(today);
		order.setAddress(Address);
		
		String summary = "";
		long price = cart.stream()
				.map(p -> p.getQuantity() * p.getProduct().getPrice())
				.collect(Collectors.toList())
				.stream().reduce(0L, (ans, i) -> ans + i);
		for (int i=0;i<cart.size();i++) {
			if(i!=cart.size()-1)
			{
			summary += cart.get(i).getProduct().getName() + "," + cart.get(i).getQuantity() + "," + cart.get(i).getProduct().getPrice() + ";";
			}
			else {
				summary += cart.get(i).getProduct().getName() + "," + cart.get(i).getQuantity() + "," + cart.get(i).getProduct().getPrice() ;

			}
		}

		order.setSummary(summary);
		order.setPrice(price);
		orderRepository.save(order);
		cartService.moveFromCartToOrder(userId);
	}
	

	@Override
	public List<Order> getAllOrderOfUser(long userId) throws UserNotExistException {

		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);

		if (!userInfo.isPresent()) {
			throw new UserNotExistException("User not found");
		}

		List<Order> res = new ArrayList<>();
		
		List<Order>dummy=orderRepository.findByUserId(userId);
		
		dummy.stream().forEach(order -> {
			String summary = order.getSummary();
			String[] subSummary = summary.split(";");
			for (String s : subSummary) {
				OrderDetails o = new OrderDetails();
				String[] subs = s.split(",");

				o.setName(subs[0]);
				o.setPrice(Long.parseLong(subs[1]));
				o.setQuantity(Integer.parseInt(subs[2]));
				order.getDescription().add(o);
			}
			res.add(order);
		});
		return res;
	}
	

	@Override
	public Order getOrderByOrderId(long oid) throws OrderNotFoundException {
		
		if (!orderRepository.findById(oid).isPresent()) {
			throw new OrderNotFoundException("Invalid Order ID");
		}

		Order order = orderRepository.findById(oid).get();

		String summary = order.getSummary();
		String[] subSummary = summary.split(";");

		for (String s : subSummary) {
			String[] subs = s.split(",");
			order.getDescription().add(new OrderDetails(subs[0], Integer.parseInt(subs[2]), Long.parseLong(subs[1])));
		}
		return order;
	}
	

	public Revenue getRevenue(Date date) {
		long sum = 0;
		List<Order> orders = new ArrayList<>();
		Timestamp t=new Timestamp(date.getTime());
		orderRepository.getAllOrderByCalendarDate(t).forEach(order -> {
			orders.add(order);
		});

		for (Order o : orders) {
			sum += o.getPrice();
		}

		Revenue revenue = new Revenue();
		revenue.setFromDate(date);
		revenue.setToDate(date);
		revenue.setPrice(sum);

		return revenue;
	}

	public Revenue getRevenueMonthly(Date date1, Date date2) {
		long sum = 0;
		List<Order> orders = new ArrayList<>();
		orderRepository.getAllOrderBetweendate(date1, date2).forEach(order -> {
			orders.add(order);
		});

		for (Order o : orders) {
			sum += o.getPrice();
		}

		Revenue revenue = new Revenue();
		revenue.setFromDate(date1);
		revenue.setToDate(date2);
		revenue.setPrice(sum);

		return revenue;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public List<Order>getAllOrders()
//	{
//		return orderdao.findAll();
//	}

}
