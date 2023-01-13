package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.OrderDto;
import com.example.dto.ResponseDto;
import com.example.model.OrderModel;
import com.example.service.IOrderService;

@RestController
@RequestMapping("/Order")
public class OrderController {

	@Autowired
	IOrderService orderService;

	// add order details
	@PostMapping("/add")
	public ResponseEntity<ResponseDto> addBook(@Valid @RequestBody OrderDto orderDto) {
		String order = orderService.addOrder(orderDto);
		ResponseDto responseDTO = new ResponseDto("Add order Success", order);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	// get all data by using find all() method
	@GetMapping("/getAll")
	public ResponseEntity<ResponseDto> findAllDetail() {
		List<OrderModel> orderList = orderService.findAll();
		ResponseDto responseDTO = new ResponseDto("** All order List ** ", orderList);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	// get orders using id
	@GetMapping("/get/{orderId}")
	public ResponseEntity<ResponseDto> FindById(@PathVariable Long orderId) {
		OrderModel response = orderService.FindById(orderId);
		ResponseDto responseDto = new ResponseDto("***All order list using Id***", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	// delete order details using id
	@DeleteMapping("/delete/{userId}/{orderId}")
	public ResponseEntity<ResponseDto> deleteById(@PathVariable Long userId, @PathVariable Long orderId) {
		orderService.deleteById(userId, orderId);
		ResponseDto reponseDTO = new ResponseDto("**order Data deleted successfully ** ", "deleted id " + orderId);
		return new ResponseEntity<ResponseDto>(reponseDTO, HttpStatus.ACCEPTED);
	}

	// update order details using order id
	@PutMapping("/update/{orderid}")
	public ResponseEntity<ResponseDto> editData(@PathVariable Long orderid, @Valid @RequestBody OrderDto orderDto) {
		String order = orderService.updateOrderData(orderid, orderDto);
		ResponseDto responseDTO = new ResponseDto("Updated Order Details Successfully", order);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
}
