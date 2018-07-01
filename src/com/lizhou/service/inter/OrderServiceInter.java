package com.lizhou.service.inter;

import java.util.List;

import com.lizhou.entity.Order;
import com.lizhou.entity.PageBean;


public interface OrderServiceInter {
	
	
	void saveOrder(Order order);
	

	List<Order> findOrder(Order s_Order, PageBean pageBean);
	

	void updateOrderStatus(String orderNo, int ststus);
}
