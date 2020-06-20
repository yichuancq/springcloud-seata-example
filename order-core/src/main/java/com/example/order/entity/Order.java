package com.example.order.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author yichuan
 */
@Entity(name = "tb_order")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 订单号码
     */
    private String orderNumber;

    /**
     * 数量
     */
    private Integer amount = 0;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 购买者Id
     */
    private Long customerId;

    /**
     * goodsId
     */
    private Long goodsId;

    /**
     *
     */
    private String goodsName;
}
