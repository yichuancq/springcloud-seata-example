package com.example.order.controller;

import com.example.commons.response.ResponseResultData;
import com.example.commons.response.ResultCode;
import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
    public ResponseResultData createOrder(Order order) {
        log.info("收到下单请求,用户:{}, 商品:{}, 下单数量:{}",
                order.getCustomerId(), order.getGoodsId(), order.getAmount());
        Boolean flag = null;
        try {
            flag = orderService.createOrder(order);
            return new ResponseResultData(ResultCode.SUCCESS, flag);
        } catch (Exception exception) {
            return new ResponseResultData(ResultCode.FAIL, flag, exception.getMessage());
        }
    }
}
