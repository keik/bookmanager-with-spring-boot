package info.keik.bookmanager.service;

import info.keik.bookmanager.model.Book;

import java.util.List;

public interface BookService {
    public void addBook(Book book);
    public void deleteBook(int id);
    public List<Book> findAllBooks();
    public Book findBookById(int id);
    public List<Book> findBooksByTitle(String string);
}
