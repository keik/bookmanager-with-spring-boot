package info.keik.bookmanager.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import info.keik.bookmanager.Application;
import info.keik.bookmanager.domain.StocksRepository;
import info.keik.bookmanager.model.Rental;
import info.keik.bookmanager.model.Stock;
import info.keik.bookmanager.model.item.Book;

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
public class RentalsServiceTest extends
        AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    RentalsService sut;

    @Autowired
    StocksRepository stocksRepository;

    @Before
    public void setup() {
        jdbcTemplate.execute("TRUNCATE SCHEMA public AND COMMIT");
    }

    @Test
    public void レンタル状態ではない在庫をレンタルできる() {
        // setup
        Stock stock1 = new Stock(10, "book", new Book("aaa1", "bbb1", "ccc1"));
        stock1.setIsOnLoan(false);
        stocksRepository.save(stock1);
        // exercise
        sut.rentStock(stock1.getId());
        // verify
        Stock rentedStock = stocksRepository.findOne(stock1.getId());
        assertThat(rentedStock.getIsOnLoan(), is(true));
    }

    @Test
    public void レンタル状態の在庫はレンタルできない() {
        // setup
        Stock stock1 = new Stock(10, "book", new Book("aaa1", "bbb1", "ccc1"));
        stock1.setIsOnLoan(true);
        stocksRepository.save(stock1);
        // exercise
        Rental ret = sut.rentStock(stock1.getId());
        // verify
        Stock rentedStock = stocksRepository.findOne(stock1.getId());
        assertThat(rentedStock.getIsOnLoan(), is(true));
        assertThat(ret, is(nullValue()));
    }

    @Test
    public void レンタル状態の在庫を返却できる() {
        // setup
        Stock stock1 = new Stock(10, "book", new Book("aaa1", "bbb1", "ccc1"));
        stock1.setIsOnLoan(true);
        stocksRepository.save(stock1);
        // exercise
        sut.returnStock(stock1.getId());
        // verify
        Stock returnedStock = stocksRepository.findOne(stock1.getId());
        assertThat(returnedStock.getIsOnLoan(), is(false));
    }

    @Test
    public void レンタル状態ではない在庫は返却できない() {
        // setup
        Stock stock1 = new Stock(10, "book", new Book("aaa1", "bbb1", "ccc1"));
        stock1.setIsOnLoan(false);
        stocksRepository.save(stock1);
        // exercise
        Rental ret = sut.returnStock(stock1.getId());
        // verify
        Stock returnedStock = stocksRepository.findOne(stock1.getId());
        assertThat(returnedStock.getIsOnLoan(), is(false));
        assertThat(ret, is(nullValue()));
    }

}
