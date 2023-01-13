package com.example.service;

import java.util.List;

import com.example.dto.OrderDto;
import com.example.model.OrderModel;

public interface IOrderService {

	String addOrder(OrderDto orderDto);

    List<OrderModel> findAll();

    OrderModel FindById(Long id);

    String deleteById(Long userId,Long id);

    String updateOrderData(Long id, OrderDto orderDto);
}
