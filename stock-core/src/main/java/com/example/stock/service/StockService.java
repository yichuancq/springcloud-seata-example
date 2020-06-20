package com.example.stock.service;

import com.example.stock.entity.Stock;

public interface StockService {
    /**
     *
     * @param stock
     * @return
     */
    Stock saveOne(Stock stock);

    Stock findByGoodsId(Long goodsId);

    /**
     *
     * @return
     */
    Stock reduceStock(Long goodsId,Integer reduceAmount);
}
