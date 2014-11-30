package info.keik.bookmanager.service;

import info.keik.bookmanager.model.Book;

import java.util.List;

public interface BooksService {
    public void addBook(Book book);

    public void deleteBook(Integer id);

    public void deleteBooks(List<Integer> ids);

    public void updateBook(Book book);

    public List<Book> findAllBooks();

    public Book findBookById(Integer id);

    public List<Book> findBooksByQuery(String string);
}
