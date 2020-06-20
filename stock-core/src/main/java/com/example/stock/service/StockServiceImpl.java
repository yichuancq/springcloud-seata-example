package com.example.stock.service;

import com.example.stock.entity.Stock;
import com.example.stock.repository.StockRepository;
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
}
