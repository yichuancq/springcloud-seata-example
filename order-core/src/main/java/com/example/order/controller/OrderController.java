package com.example.order.controller;

import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yichuan
 */
@Api(value = "订单信息")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 生成订单信息
     *
     * @param order
     * @return
     */
    @ApiOperation(value = "createOrder", notes = "生成订单信息")
    @GetMapping(value = "/createOrder")
    public Boolean createOrder(Order order) {
        return orderService.createOrder(order);
    }
}
