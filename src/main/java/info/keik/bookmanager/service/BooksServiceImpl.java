package info.keik.bookmanager.service;

import info.keik.bookmanager.domain.BooksRepository;
import info.keik.bookmanager.model.Book;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Override
    @Transactional
    public void addBook(Book book) {
        booksRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(Integer id) {
        booksRepository.delete(id);
    }

    @Override
    @Transactional
    public void deleteBooks(List<Integer> ids) {
        booksRepository.deleteByIdIn(ids);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        Book target = booksRepository.findOne(book.getId());
        if (target == null) {

            // TODO
            throw new RuntimeException("TODO");
        }
        booksRepository.save(book);
    }

    @Override
    public List<Book> findAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    public Book findBookById(Integer id) {
        return booksRepository.getOne(id);
    }

    @Override
    public List<Book> findBooksByTitle(String query) {
        return booksRepository.findByTitleContaining(query);
    }

}
