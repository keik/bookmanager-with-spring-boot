package info.keik.bookmanager.domain;

import info.keik.bookmanager.model.Book;
import info.keik.bookmanager.model.Tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContaining(String title);

    List<Book> findByPublisher(String publisher);

    List<Book> findByAuthor(String author);

    @Transactional
    List<Book> deleteByIdIn(List<Integer> ids);

    @Query(value = "SELECT b FROM Book b WHERE b.title = 'Land of Lisp'")
    List<Book> findHoge();

    @Query(value = "SELECT b FROM Book b WHERE b.tags IN :tags")
    List<Book> findByTag(@Param("tags") List<Tag> tags);

    @Query(value = "SELECT b FROM Book b WHERE :tag MEMBER OF b.tags ")
    List<Book> findByTag2(@Param("tag") Tag tag);

    @Query(value = "SELECT b FROM Book b WHERE b.title IN :titles")
    List<Book> findByTag3(@Param("titles") List<String> titles);

    @Query(value = "SELECT b FROM Book b WHERE b.title IN :titles AND :tag MEMBER OF b.tags ")
    List<Book> findByTag4(@Param("titles") List<String> titles,
            @Param("tag") Tag tag);
}
