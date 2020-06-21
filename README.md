# springcloud-seata-example
分布式事务和并发访问

>重要：使用前置条件，seata服务正常开启，nacos 服务开启，seata注册到nacos上的

> 注册文件配置
```text
registry {
  # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
  type = "file"
  file {
    name = "file.conf"
  }
}

config {
  # file、nacos 、apollo、zk、consul、etcd3、springCloudConfig
  type = "file"
  file {
    name = "file.conf"
  }
}

```

>添加 @GlobalTransactional注解在方法上，开启全局事务
```text
@GlobalTransactional
```

> FeignClient调用库存服务
```java
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
```
>配置分布式事务组
```text
#配置分布式事务组,要在spring下
cloud:
alibaba:
  seata:
    application-id: ${spring.application.name} # Seata 应用编号，默认为 ${spring.application.name}
    #application的值就是注册中心服务的名称，客户端通过这个名称找到事务处理的服务，可以自己定义，但需要注意的是，file:
    #conf中的，service里边配置的事务组应该跟服务名保持一致
    tx-service-group: my_test_tx_group
```

>Request URL
```html
http://localhost:18002/order/createOrder?orderNumber=1&amount=1&price=1232&customerId=1&goodsId=1&goodsName=name
```

>分布式事务和并发访问
```json
{
  "code": 1,
  "message": "操作成功！",
  "data": true,
  "resultMap": null
}
```

>order下单请求

```text
# 下单请求
收到下单请求,用户:1, 商品:1, 下单数量:1
Begin new global transaction [172.17.0.3:8091:17985717238349824]
[createOrder] 当前 XID: 172.17.0.3:8091:17985717238349824
response:StockDto(id=1, goodsId=1, goodsName=est, stockAmount=998409)
# 插入订单
update hibernate_sequence set next_val= ? where next_val=?
insert into tb_order (create_time, update_time, version, amount, customer_id, goods_id, goods_name, order_number, price, id)
 values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
[172.17.0.3:8091:17985717238349824] commit status: Committed
```

>库存扣减
```text
库存查询: 商品Id:1
收到库存冲减请求，商品:1, 数量:1
select stock0_.id as id1_0_, stock0_.create_time as create_t2_0_, stock0_.update_time as update_t3_0_, stock0_.version as version4_0_, stock0_.goods_id as goods_id5_0_, stock0_.goods_name as goods_na6_0_, stock0_.stock_amount as stock_am7_0_ 
 from tb_stock stock0_ 
 where stock0_.goods_id=?
[reduceStock] 当前 XID: 172.17.0.3:8091:17985717238349824
update tb_stock set stock_amount=stock_amount-? where goods_id=? and stock_amount>0
```