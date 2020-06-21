package com.example.stock.service;

import com.example.stock.entity.Stock;
import com.example.stock.repository.StockRepository;
import io.seata.core.context.RootContext;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yichuan
 */
@Service
@Slf4j
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Stock saveOne(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock findByGoodsId(Long goodsId) {
        return stockRepository.findByGoodsId(goodsId);
    }

    @Transactional // 开启事物
    @Synchronized
    @Override
    public Stock reduceStock(Long goodsId, Integer reduceAmount) {

        log.info("[reduceStock] 当前 XID: {}", RootContext.getXID());
        Stock stock = stockRepository.findByGoodsId(goodsId);
        if (stock.getGoodsId().equals(goodsId) && reduceAmount <= stock.getStockAmount()) {
            Integer leaveAmount = stock.getStockAmount() - reduceAmount;
            stock.setStockAmount(leaveAmount);
            stock = stockRepository.save(stock);
        }
        log.info("商品:{},库存冲减后:{}", stock.getGoodsId(), stock.getStockAmount());
        return stock;
    }

    /**
     * @param stockAmount
     * @param goodsId
     */

    @Override
    public void updateStock(Long goodsId, Integer stockAmount) {
        log.info("[reduceStock] 当前 XID: {}", RootContext.getXID());
        stockRepository.updateStock(goodsId, stockAmount);
    }
}
