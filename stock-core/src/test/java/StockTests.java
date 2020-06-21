import com.example.stock.StockApplication;
import com.example.stock.entity.Stock;
import com.example.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StockApplication.class)
public class StockTests {

    @Autowired
    private StockService stockService;


    @Test
    public void reduceStock() {
        stockService.updateStock(1L, 1);
    }


    @Test
    public void findByGoodsId() {
        Stock stock = stockService.findByGoodsId(1L);
        if (stock != null)
            log.info(stock.toString());
    }

    @Test
    public void saveOne() {

        Stock stock = new Stock();
        stock.setGoodsId(1L);
        stock.setGoodsName("est");
        stock.setStockAmount(10000);
        Stock dbSock = stockService.saveOne(stock);
        if (dbSock != null)
            log.info("goods:{}", dbSock.toString());
    }
}
