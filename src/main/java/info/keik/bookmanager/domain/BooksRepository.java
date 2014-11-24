package info.keik.bookmanager.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.keik.bookmanager.model.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContaining(String title);
    List<Book> findByPublisher(String publisher);
    List<Book> findByAuthor(String author);

}
