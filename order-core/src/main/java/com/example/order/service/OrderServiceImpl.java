package com.example.order.service;

import com.example.commons.dto.StockDto;
import com.example.order.entity.Order;
import com.example.order.feign.StorageFeignClient;
import com.example.order.repository.OrderRepository;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author yichuan
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StorageFeignClient feignClient;

    @Autowired
    private OrderRepository orderRepository;

    @GlobalTransactional
    @Override
    public Boolean createOrder(Order order) throws Exception {
        try {
            log.info("[createOrder] 当前 XID: {}", RootContext.getXID());
            //判断订单数量是否合法
            if (order == null || order.getAmount() <= 0) {
                throw new Exception("订单非法");
            }
            //判断库存 是否充足
            Integer inputStock = order.getAmount();
            // 执行调用
            StockDto stockDto = feignClient.queryStock(order.getGoodsId());
            if (stockDto == null) {
                throw new RuntimeException("扣除库存失败");
            }
            log.info("response:{}", stockDto);
            if (stockDto == null) {
                throw new Exception("获取库存失败");
            }
            if (stockDto.getStockAmount() <= 0 || stockDto.getStockAmount() < inputStock) {
                throw new Exception("库存不足");
            }
            //save order
            orderRepository.save(order);
            //扣减库存
            feignClient.reduceStock(order.getGoodsId(), inputStock);
            return true;
        } catch (Exception exception) {
            log.error("ex:{}", exception.getMessage());
            exception.printStackTrace();
            throw exception;
        }
    }

    @Override
    public void saveAll(List<Order> orderList) {
        orderRepository.saveAll(orderList);
    }

    @Override
    public void saveOne(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }

    @Override
    public Order findOneById(Long id) {
        Optional<Order> goodsOptional = orderRepository.findById(id);
        if (goodsOptional.isPresent()) {
            return goodsOptional.get();
        }
        return null;
    }
}
