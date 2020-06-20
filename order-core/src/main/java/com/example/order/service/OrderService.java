package com.example.order.service;


import com.example.order.entity.Order;

import java.util.List;

/**
 * @author yichuan
 */
public interface OrderService {

    /**
     * saveAll
     *
     * @param orderList
     */
    void saveAll(List<Order> orderList);


    void saveOne(Order order);
    /**
     * deleteAll
     */
    void deleteAll();

    /**
     * @param id
     * @return
     */
    Order findOneById(Long id);
}
