package com.example.order.service;


import com.example.order.entity.Order;

import java.util.List;

/**
 * @author yichuan
 */
public interface OrderService {

    /**
     * 生成订单信息
     *
     * @param order
     * @return
     */
    Boolean createOrder(Order order);

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
