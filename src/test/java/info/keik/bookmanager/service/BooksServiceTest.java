package info.keik.bookmanager.service;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import info.keik.bookmanager.Application;
import info.keik.bookmanager.domain.BooksRepository;
import info.keik.bookmanager.model.Book;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class BooksServiceTest extends
        AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BooksService sut;

    @Autowired
    BooksRepository booksRepository;

    @Before
    public void setup() {
        jdbcTemplate.execute("TRUNCATE SCHEMA public AND COMMIT");
    }

    @Test
    public void 本を追加できる() {
        // setup
        // exercise
        sut.addBook(new Book("aaa1", "bbb1", "ccc1"));
        // verify
        assertThat(sut.findAllBooks(), is(hasSize(1)));
    }

    @Test
    public void IDで指定した本を削除できる() {
        // setup
        Book book1 = new Book("aaa1", "bbb1", "ccc1");
        Book book2 = new Book("aaa2", "bbb2", "ccc2");
        sut.addBook(book1);
        sut.addBook(book2);
        // exercise
        sut.deleteBook(book1.getId());
        // verify
        assertThat(sut.findAllBooks(), is(hasSize(1)));
    }

    @Test
    public void 複数のIDで指定した本を削除できる() {
        // setup
        Book book1 = new Book("aaa1", "bbb1", "ccc1");
        Book book2 = new Book("aaa2", "bbb2", "ccc2");
        Book book3 = new Book("aaa3", "bbb3", "ccc3");
        sut.addBook(book1);
        sut.addBook(book2);
        sut.addBook(book3);
        // exercise
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(book1.getId());
        ids.add(book3.getId());
        sut.deleteBooks(ids);
        // verify
        assertThat(sut.findAllBooks(), is(hasSize(1)));
    }

    @Test
    public void 本の情報を更新できる() {
        // setup
        Book book1 = new Book("aaa1", "bbb1", "ccc1");
        sut.addBook(book1);
        // exercise
        Book newbook = new Book("xxx1", "yyy1", "zzz1");
        newbook.setId(book1.getId());
        sut.updateBook(newbook);
        // verify
        Book updated = sut.findBookById(book1.getId());
        assertThat(updated, is(samePropertyValuesAs(book1)));
    }

    @Test
    public void 全ての本を取得できる() {
        // setup
        sut.addBook(new Book("aaa1", "bbb1", "ccc1"));
        sut.addBook(new Book("aaa2", "bbb2", "ccc2"));
        sut.addBook(new Book("aaa3", "bbb3", "ccc3"));
        sut.addBook(new Book("aaa4", "bbb4", "ccc4"));
        // exercise
        // verify
        assertThat(sut.findAllBooks(), hasSize(4));
    }

    @Test
    public void IDで指定した本を取得できる() {
        // setup
        Book book1 = new Book("aaa1", "bbb1", "ccc1");
        Book book2 = new Book("aaa2", "bbb2", "ccc2");
        Book book3 = new Book("aaa3", "bbb3", "ccc3");
        sut.addBook(book1);
        sut.addBook(book2);
        sut.addBook(book3);
        // exercise
        Book actual = sut.findBookById(book2.getId());
        // verify
        assertThat(actual, is(samePropertyValuesAs(book2)));
    }

    @Test
    public void タイトルで検索した本を取得できる() {
        // setup
        sut.addBook(new Book("aaa1", "bbb1", "ccc1"));
        sut.addBook(new Book("aaa2", "bbb2", "ccc2"));
        sut.addBook(new Book("xxx1", "bbb1", "ccc1"));
        sut.addBook(new Book("xxx2", "bbb2", "ccc2"));
        sut.addBook(new Book("yyy1", "bbb1", "ccc1"));
        sut.addBook(new Book("yyy2", "bbb2", "ccc2"));
        // exercise
        List<Book> finded1 = sut.findBooksByQuery("xxx");
        List<Book> finded2 = sut.findBooksByQuery("xxx2");
        // verify
        assertThat(finded1, is(hasSize(2)));
        assertThat(finded2, is(hasSize(1)));
        assertThat(finded2.get(0).getTitle(), is("xxx2"));
    }

}
