package info.keik.bookmanager.service;

import info.keik.bookmanager.model.Book;

import java.util.List;

public interface BooksService {

    /**
     * Add a book
     * 
     * @param book
     *            A book to add
     */
    public void addBook(Book book);

    /**
     * Delete a book
     * 
     * @param id
     *            Book ID to delete
     */
    public void deleteBook(Integer id);

    /**
     * Delete books
     * 
     * @param ids
     *            Book IDs to delete
     */
    public void deleteBooks(List<Integer> ids);

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

    /**
     * Find books by title (temporary) TODO #9
     * 
     * @param string
     *            A Parts of titles
     * @return Found books
     */
    public List<Book> findBooksByTitle(String string);

}
