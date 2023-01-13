package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dto.BookDto;
import com.example.dto.OrderDto;
import com.example.dto.UserDto;
import com.example.exception.OrderException;
import com.example.model.OrderModel;
import com.example.repository.IOrderRepo;
import com.example.utility.EmailSenderService;
import com.example.utility.JwtTokenUtil;

@Service
public class OrderService implements IOrderService {

	@Autowired
	IOrderRepo orderRepo;

	@Autowired
	JwtTokenUtil tokenUtil;
	@Autowired
	EmailSenderService emailSenderService;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public String addOrder(OrderDto orderDto) {
		UserDto userData = restTemplate.getForObject("http://localhost:8081/User/Get/" + orderDto.getUserId(),
				UserDto.class);
		BookDto bookDetails = restTemplate.getForObject("http://localhost:8082/Book/get/" + orderDto.getBookId(),
				BookDto.class);
		if (userData.equals(null) && bookDetails.equals(null)) {
			throw new OrderException("invalid user Id and BookID");
		} else {
			OrderModel orderDetails = new OrderModel(orderDto);
			String token = tokenUtil.createToken(orderDetails.getOrderId());
			emailSenderService.sendEmail(userData.getEmail_address(), "Added Your Details",
					"http://localhost:8081/User/retrieve/" + token);
			return token;
		}
	}

	@Override
	public List<OrderModel> findAll() {
		List<OrderModel> orderdetails = orderRepo.findAll();
		return orderdetails;
	}

	@Override
	public OrderModel FindById(Long id) {
		OrderModel order = orderRepo.findById(id).orElse(null);
		if (order != null) {
			return order;

		} else
			throw new OrderException("order id is not found");
	}

	@Override
	public String deleteById(Long userId, Long id) {

		OrderModel findById = orderRepo.findById(id).orElse(null);
		UserDto userData = restTemplate.getForObject("http://localhost:8081/User/Get/" + userId, UserDto.class);

		if (findById != null && userData != null) {
			if (userData.getUserId().equals(findById.getUserId())) {
				orderRepo.deleteById(id);
				return "data is deleted";
			} else
				throw new OrderException("user id does not match");

		} else
			throw new OrderException("Order id is  invalid");

	}

	@Override
	public String updateOrderData(Long id, OrderDto orderDto) {
		UserDto userData = restTemplate.getForObject("http://localhost:8081/User/Get/" + orderDto.getUserId(),
				UserDto.class);
		BookDto bookDetails = restTemplate.getForObject("http://localhost:8082/Book/get/" + orderDto.getBookId(),
				BookDto.class);
		OrderModel editdata = orderRepo.findById(id).orElse(null);
		if (userData != null && bookDetails != null && editdata != null) {
			editdata.setBookId(orderDto.getBookId());
			editdata.setUserId(orderDto.getUserId());
			editdata.setQuantity(orderDto.getQuantity());
			String token = tokenUtil.createToken(editdata.getOrderId());
			emailSenderService.sendEmail(userData.getEmail_address(), "Added Your Details",
					"http://localhost:8081/User/retrieve/" + token);
			return token;
		}

		else {

			throw new OrderException(" id is invalid");
		}
	}
}
