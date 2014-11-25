package info.keik.bookmanager.service;

import java.util.List;

import info.keik.bookmanager.model.Book;

public interface BooksService {
    public void addBook(Book book);
    public void deleteBook(int id);
    public void deleteBooks(List<Integer> ids);
    public void updateBook(Book book);
    public List<Book> findAllBooks();
    public Book findBookById(int id);
    public List<Book> findBooksByTitle(String string);
}
