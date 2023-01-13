package com.example.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {

	 int quantity;
	    String address;
	    Long userId;
	    Long bookId;
	    LocalDate Date=LocalDate.now();
	    boolean cancel;
	    int price;
}
