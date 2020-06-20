package com.example.goods.service;

import com.example.goods.entity.BaseEntity.Goods;
import com.example.goods.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author yichuan
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public void saveAll(List<Goods> goodsLis) {
        goodsRepository.saveAll(goodsLis);
    }

    @Override
    public void deleteAll() {
        goodsRepository.deleteAll();
    }

    @Override
    public Goods findOneById(Long id) {
        Optional<Goods> goodsOptional = goodsRepository.findById(id);
        if (goodsOptional.isPresent()) {
            return goodsOptional.get();
        }
        return null;
    }
}
