package com.pn.food_cart_management.repository;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pn.food_cart_management.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
 
	
	public List<Order> findByUserId(long userId);
	
	@Query("Select o from Order o where DATE(o.calendarDate)=?1")
     public List<Order> getAllOrderByCalendarDate(Timestamp date);
//	
//	
	@Query("Select o from Order o where DATE(o.calendarDate) between ?1 and ?2 ")
    public List<Order> getAllOrderBetweendate(Date date1,Date date2);

}
