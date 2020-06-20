package com.example.commons.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockDto extends BaseEntity{

    private Long id;

    private Long goodsId;

    private String goodsName;
    /**
     * 库存数量
     */
    private Integer stockAmount;

}
