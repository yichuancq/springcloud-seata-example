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

    @ApiOperation(value = "queryStock", notes = "库存信息查询")
    @GetMapping(value = "/queryStock")
    public Stock queryStock(Long goodsId) {
        return stockService.findByGoodsId(goodsId);
    }
}
