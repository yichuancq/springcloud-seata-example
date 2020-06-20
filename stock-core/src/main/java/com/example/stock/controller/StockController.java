package com.example.stock.controller;

import com.example.stock.entity.Stock;
import com.example.stock.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yichuan
 */
@Api(value = "库存信息")
@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    /**
     * 查询库存
     *
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "queryStock", notes = "库存信息查询")
    @GetMapping(value = "/queryStock")
    public Stock queryStock(Long goodsId) {
        return stockService.findByGoodsId(goodsId);
    }

    /**
     * 库存冲减
     *
     * @param goodsId
     * @param reduceAmount
     * @return
     */
    @ApiOperation(value = "reduceStock", notes = "库存扣减")
    @GetMapping(value = "/reduceStock")
    public Stock reduceStock(Long goodsId, Integer reduceAmount) {
        return stockService.reduceStock(goodsId, reduceAmount);
    }
}
