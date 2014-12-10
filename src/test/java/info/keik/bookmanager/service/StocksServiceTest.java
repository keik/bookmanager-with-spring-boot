package info.keik.bookmanager.service;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import info.keik.bookmanager.Application;
import info.keik.bookmanager.domain.StocksRepository;
import info.keik.bookmanager.model.Stock;
import info.keik.bookmanager.model.item.Book;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class StocksServiceTest extends
        AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    StocksService sut;

    @Autowired
    StocksRepository stocksRepository;

    @Before
    public void setup() {
        jdbcTemplate.execute("TRUNCATE SCHEMA public AND COMMIT");
    }

    @Test
    public void 全てのを取得できる() {
        // setup
        Stock stock1 = new Stock(10, "book", new Book("aaa1", "bbb1", "ccc1"));
        Stock stock2 = new Stock(20, "book", new Book("aaa2", "bbb2", "ccc2"));
        Stock stock3 = new Stock(30, "book", new Book("aaa3", "bbb3", "ccc3"));
        sut.addStock(stock1);
        sut.addStock(stock2);
        sut.addStock(stock3);
        // exercise
        // verify
        assertThat(stocksRepository.findAll(), hasSize(3));
    }

    @Test
    public void IDで指定した本を取得できる() {
        // setup
        Stock stock1 = new Stock(10, "book", new Book("aaa1", "bbb1", "ccc1"));
        Stock stock2 = new Stock(20, "book", new Book("aaa2", "bbb2", "ccc2"));
        Stock stock3 = new Stock(30, "book", new Book("aaa3", "bbb3", "ccc3"));
        sut.addStock(stock1);
        sut.addStock(stock2);
        sut.addStock(stock3);
        // exercise
        Stock finded = sut.findStockById(stock2.getId());
        // verify
        assertThat(finded, is(samePropertyValuesAs(stock2)));
    }

    @Test
    public void タイトルで検索した本を取得できる() {
        // setup
        sut.addStock(new Stock(10, "book", new Book("aaa1", "bbb1", "ccc1")));
        sut.addStock(new Stock(20, "book", new Book("aaa2", "bbb2", "ccc2")));
        sut.addStock(new Stock(30, "book", new Book("xxx1", "bbb1", "ccc1")));
        sut.addStock(new Stock(40, "book", new Book("xxx2", "bbb2", "ccc2")));
        sut.addStock(new Stock(50, "book", new Book("yyy1", "bbb1", "ccc1")));
        sut.addStock(new Stock(60, "book", new Book("yyy2", "bbb2", "ccc2")));
        // exercise
        List<Stock> finded1 = sut.findStocksByQuery("xxx");
        List<Stock> finded2 = sut.findStocksByQuery("xxx2");
        // verify
        assertThat(finded1, is(hasSize(2)));
        assertThat(finded2, is(hasSize(1)));
        assertThat(finded2.get(0).getItem().getName(), is("xxx2"));
    }

    @Test
    public void 在庫を追加できる() {
        // setup
        // exercise
        sut.addStock(new Stock(10, "book", new Book("aaa1", "bbb1", "ccc1")));
        // verify
        assertThat(stocksRepository.findAll(), is(hasSize(1)));
    }

    @Test
    public void IDで指定した在庫を削除できる() {
        // setup
        Stock stock1 = new Stock(10, "book", new Book("aaa1", "bbb1", "ccc1"));
        Stock stock2 = new Stock(20, "book", new Book("aaa2", "bbb2", "ccc2"));
        sut.addStock(stock1);
        sut.addStock(stock2);

        // exercise
        sut.deleteStock(stock1.getId());
        // verify
        assertThat(stocksRepository.findAll(), is(hasSize(1)));
    }

    @Test
    public void 複数のIDで指定した在庫を削除できる() {
        // setup
        Stock stock1 = new Stock(10, "book", new Book("aaa1", "bbb1", "ccc1"));
        Stock stock2 = new Stock(20, "book", new Book("aaa2", "bbb2", "ccc2"));
        Stock stock3 = new Stock(30, "book", new Book("aaa3", "bbb3", "ccc3"));
        sut.addStock(stock1);
        sut.addStock(stock2);
        sut.addStock(stock3);
        // exercise
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(stock1.getId());
        ids.add(stock3.getId());
        sut.deleteStocks(ids);
        // verify
        assertThat(stocksRepository.findAll(), is(hasSize(1)));
    }

}
