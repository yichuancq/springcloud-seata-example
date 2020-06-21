package com.example.order.feign;

import com.example.commons.dto.StockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yichuan
 */
@FeignClient(name = "stock-service", url = "127.0.0.1:18003")
public interface StorageFeignClient {

    /**
     * @param goodsId
     * @return
     */
    @PostMapping(value = "/stock/queryStock")
    public StockDto queryStock(@RequestParam("goodsId") Long goodsId);

    /**
     * @param reduceAmount
     * @param reduceAmount
     * @return
     */
    @PostMapping(value = "/stock/reduceStock2")
    public StockDto reduceStock(@RequestParam("goodsId") Long goodsId,
                                @RequestParam("reduceAmount") Integer reduceAmount);

}