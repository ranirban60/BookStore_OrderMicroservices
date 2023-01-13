package com.example.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.dto.OrderDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "book_order")
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderId", nullable = false)
	Long orderId;
	LocalDate date = LocalDate.now();
	int price;
	int quantity;
	String address;

	Long userId;
	Long bookId;
	boolean cancel;

	public OrderModel(OrderDto orderDto) {
		this.date = orderDto.getDate();
		this.price = orderDto.getPrice();
		this.quantity = orderDto.getQuantity();
		this.address = orderDto.getAddress();
		this.userId = orderDto.getUserId();
		this.bookId = orderDto.getBookId();
		this.cancel = orderDto.isCancel();
	}
}
