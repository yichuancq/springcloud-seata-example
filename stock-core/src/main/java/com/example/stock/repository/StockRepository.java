package com.example.stock.repository;

import com.example.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository  extends JpaRepository<Stock, Long> {

    Stock  findByGoodsId(Long goodsId);
}
