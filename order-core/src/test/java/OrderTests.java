
import com.example.order.OrderApplication;
import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class OrderTests {
    @Autowired
    private OrderService orderService;

    @Test
    public void finById() {
        Order order = orderService.findOneById((long) 1);
        if (order != null)
            log.info("goods:{}", order.toString());
    }

    @Test
    public void testSaveAll() {

        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setGoodsId(1L);
        order.setGoodsName("大保健1");
        order.setOrderNumber("order1");
        order.setAmount(1);
        order.setCustomerId(1L);
        order.setPrice(BigDecimal.valueOf(0.13));
        orders.add(order);
        orderService.deleteAll();
        orderService.saveAll(orders);
    }
}
