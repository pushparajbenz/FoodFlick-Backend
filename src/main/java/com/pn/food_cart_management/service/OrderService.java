package com.pn.food_cart_management.service;

import java.util.Date;
import java.util.List;

import com.pn.food_cart_management.entity.Order;
import com.pn.food_cart_management.utils.Revenue;

public interface OrderService {
		
	
	public void addOrder(long userId,String Address);
	public List<Order>getAllOrderOfUser(long userId);
	public Order getOrderByOrderId(long oid);
	
 	public Revenue getRevenue(Date date);
	public Revenue getRevenueMonthly(Date date1,Date date2);	

}
