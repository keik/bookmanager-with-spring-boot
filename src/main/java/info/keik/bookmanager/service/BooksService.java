package info.keik.bookmanager.service;

import info.keik.bookmanager.model.item.Book;

import java.util.List;

public interface BooksService {

    /**
     * Update a book
     * 
     * @param book
     *            A book to update
     */
    public void updateBook(Book book);

    /**
     * Find all books
     * 
     * @return All books
     */
    public List<Book> findAllBooks();

    /**
     * Find a book by ID
     * 
     * @param id
     *            Book ID to find
     * @return A found book
     */
    public Book findBookById(Integer id);

}
