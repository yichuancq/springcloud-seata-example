import com.example.goods.GoodsApplication;
import com.example.goods.entity.BaseEntity.Goods;
import com.example.goods.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GoodsApplication.class)
public class GoodsTests {
    @Autowired
    private GoodsService goodsService;

    @Test
    public void finById() {
        Goods goods = goodsService.findOneById((long) 1);
        if (goods != null)
            log.info("goods:{}", goods.toString());
    }
    @Test
    public void testSaveAll() {

        List<Goods> goodsLis = new ArrayList<>();
        Goods goods1 = Goods.builder().goodsName("goods1").build();
        Goods goods2 = Goods.builder().goodsName("goods2").build();
        goodsLis.add(goods1);
        goodsLis.add(goods2);
        goodsService.deleteAll();
        goodsService.saveAll(goodsLis);
    }
}
