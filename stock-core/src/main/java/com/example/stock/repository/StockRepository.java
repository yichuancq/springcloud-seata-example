package com.example.stock.repository;

import com.example.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findByGoodsId(Long goodsId);

    ////修改商品库存时判断库存是否大于0
    //update t_goods set stock=stock-1 where id=1 and stock>0;
    @Transactional
    @Modifying
    @Query(value = "update tb_stock t set stock_amount=" +
            " stock_amount - ?2 where goods_id=?1 and stock_amount>0")
    public void updateStock(Long goodsId, Integer stockAmount);
}
