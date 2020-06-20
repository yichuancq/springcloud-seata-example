package com.example.goods.service;

import com.example.goods.entity.BaseEntity.Goods;

import java.util.List;

/**
 * @author yichuan
 */
public interface GoodsService {

    /**
     * saveAll
     *
     * @param goodsLis
     */
    void saveAll(List<Goods> goodsLis);

    /**
     * deleteAll
     */
    void deleteAll();

    /**
     * @param id
     * @return
     */
    Goods findOneById(Long id);
}
