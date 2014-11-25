package info.keik.bookmanager.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.keik.bookmanager.domain.BooksRepository;
import info.keik.bookmanager.model.Book;

@Service
public class BooksServiceImpl implements BooksService {

    private static final Logger logger = LoggerFactory.getLogger(BooksServiceImpl.class);

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public void addBook(Book book) {
        logger.info("addBook");
        booksRepository.save(book);
    }

    @Override
    public void deleteBook(Integer id) {
        logger.info("deleteBook");
        booksRepository.delete(id);
    }

    @Override
    public void deleteBooks(List<Integer> ids) {
        logger.info("deleteBooks");
        booksRepository.deleteByIdIn(ids);
    }

    @Override
    public void updateBook(Book book) {
        Book target = booksRepository.findOne(book.getId());
        if (target == null) {

            // TODO
            throw new RuntimeException("hoho");
        }
        booksRepository.save(book);
    }

    @Override
    public List<Book> findAllBooks() {
        logger.info("findAllBooks");

        return booksRepository.findAll();
    }

    @Override
    public Book findBookById(Integer id) {
        logger.info("findBookById");
        return booksRepository.getOne(id);
    }

    @Override
    public List<Book> findBooksByTitle(String query) {
        logger.info("findBooksByTitle");
        return booksRepository.findByTitleContaining(query);
    }
}
