package info.keik.bookmanager.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import info.keik.bookmanager.model.Book;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private static final List<Book> books = new ArrayList<Book>() {{
            add(new Book("Spring3入門", "長谷川裕一", "技術評論社"));
            add(new Book("計算幾プログラムの構造と解釈", "ジェラルド・ジェイ・サスマン", "ピアソン"));
            add(new Book("軽快なJava", "ブルース・A. テイト", "オライリージャパン"));
        }};

    @Override
    public void addBook(Book book) {
        logger.info("addBook");
        books.add(book);
    }

    @Override
    public void deleteBook(int id) {
        logger.info("deleteBook");
        books.remove(id);
    }

    @Override
    public List<Book> findAllBooks() {
        logger.info("findAllBooks");
        return books;
    }

    @Override
    public List<Book> findBooksByTitle(String query) {
        logger.info("findBooksByTitle");
        List<Book> rets = new ArrayList<Book>();
        for (Book book : books) {
            if (book.getTitle().indexOf(query) > -1) {
                rets.add(book);
            }
        }
        return rets;
    }
}
