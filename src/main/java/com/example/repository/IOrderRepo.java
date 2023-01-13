package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.OrderModel;

@Repository
public interface IOrderRepo extends JpaRepository<OrderModel, Long> {

}
