package com.example.stock.service;

import com.example.stock.entity.Stock;
import com.example.stock.repository.StockRepository;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yichuan
 */
@Service
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

    @Synchronized
    @Override
    public Stock reduceStock(Long goodsId, Integer reduceAmount) {
        Stock stock = stockRepository.findByGoodsId(goodsId);
        if (stock.getGoodsId().equals(goodsId) && reduceAmount <= stock.getStockAmount()) {
            Integer leaveAmount = stock.getStockAmount() - reduceAmount;
            stock.setStockAmount(leaveAmount);
            stock = stockRepository.save(stock);
        }
        return stock;
    }
}
