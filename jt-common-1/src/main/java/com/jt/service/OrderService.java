package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.pojo.Order;

@Service
public interface OrderService {

	String saveOrder(Order order);

	Order findOrderById(String id);

}
