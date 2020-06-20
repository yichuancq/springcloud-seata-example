package com.example.order.repository;

import com.example.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yichuan
 */
public interface OrderRepository  extends JpaRepository<Order, Long> {
}
