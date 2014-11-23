package info.keik.bookmanager.service;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.keik.bookmanager.domain.BookRepository;
import info.keik.bookmanager.model.Book;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void addBook(Book book) {
        logger.info("addBook");
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(int id) {
        logger.info("deleteBook");
        bookRepository.delete(id);
    }

    @Override
    public List<Book> findAllBooks() {
        logger.info("findAllBooks");

        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(int id) {
        logger.info("findBookById");
        return bookRepository.getOne(id);
    }

    @Override
    public List<Book> findBooksByTitle(String query) {
        logger.info("findBooksByTitle");
        return bookRepository.findByTitleContaining(query);
    }
}
