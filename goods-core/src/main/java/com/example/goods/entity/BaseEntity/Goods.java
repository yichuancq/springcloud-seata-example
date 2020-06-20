package com.example.goods.entity.BaseEntity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * @author yichuan
 */
@Entity(name = "tb_goods")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Goods extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    //
    private String goodsName;

}
