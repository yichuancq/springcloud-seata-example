package com.example.goods.repository;

import com.example.goods.entity.BaseEntity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商品信息
 *
 * @author yichuan
 */
public interface GoodsRepository extends JpaRepository<Goods, Long> {

}
