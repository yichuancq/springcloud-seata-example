package com.example.order.service;

import com.example.commons.dto.StockDto;
import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author yichuan
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    @Override
    public Boolean createOrder(Order order) {
        try {

            //判断订单数量是否合法
            if (order == null || order.getAmount() <= 0) {
                throw new Exception("订单非法");
            }
            //判断库存 是否充足
            Integer inputStock = order.getAmount();
            final String url = "http://localhost:18003/stock/queryStock?goodsId=" + order.getGoodsId();
            StockDto stockDto = restTemplate.getForObject(url, StockDto.class);
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
            final String url2 = "http://localhost:18003/stock/reduceStock?goodsId=" + order.getGoodsId() + "&reduceAmount=" + inputStock;
            StockDto stockDtoLeave = restTemplate.getForObject(url2, StockDto.class);
            if (stockDtoLeave == null) {
                throw new Exception("库存冲减失败");
            }
            log.error("库存冲减后:{}", stockDtoLeave.toString());
            return true;
        } catch (Exception exception) {
            log.error("ex:{}", exception.getMessage());
            exception.printStackTrace();
        }
        //Order
        return false;
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
