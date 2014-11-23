package info.keik.bookmanager.service;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import info.keik.bookmanager.Application;
import info.keik.bookmanager.model.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class BookServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    BookService sut;

    @Before
    public void setup() {
        deleteFromTables("BOOK");
    }

    @Test
    public void 本を追加できる() {
        // setup
        // exercise
        sut.addBook(new Book("aaa1", "bbb1", "ccc1"));
        // verify
        assertThat(sut.findAllBooks(), is(hasSize(1)));
        // teardown
    }

    @Test
    public void 本を削除できる() {
        // setup
        Book book1 = new Book("aaa1", "bbb1", "ccc1");
        sut.addBook(book1);
        sut.addBook(new Book("aaa2", "bbb2", "ccc2"));
        // exercise
        sut.deleteBook(book1.getId());
        // verify
        assertThat(sut.findAllBooks(), is(hasSize(1)));
        // teardown
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
        assertThat(sut.findAllBooks().size(), is(Integer.valueOf(4)));
        // teardown
    }

    @Test
    public void IDで指定した本を取得できる() {
        // setup
        sut.addBook(new Book("aaa1", "bbb1", "ccc1"));
        Book book2 = new Book("aaa2", "bbb2", "ccc2");
        sut.addBook(book2);
        sut.addBook(new Book("aaa3", "bbb3", "ccc3"));
        // exercise
        Book actual = sut.findBookById(book2.getId());
        // verify
        assertThat(actual, is(samePropertyValuesAs(book2)));
        // teardown
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
        List<Book> actual = sut.findBooksByTitle("xxx");
        List<Book> actual2 = sut.findBooksByTitle("xxx2");
        // verify
        assertThat(actual, is(hasSize(2)));
        assertThat(actual2, is(hasSize(1)));
        assertThat(actual2.get(0).getTitle(), is("xxx2"));
        // teardown
    }

}
