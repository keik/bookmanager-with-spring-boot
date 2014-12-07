package info.keik.bookmanager.domain;

import info.keik.bookmanager.model.item.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    @Transactional
    List<Book> findByNameContaining(String title);

    @Transactional
    List<Book> deleteByIdIn(List<Integer> ids);

    // @Query(value = "SELECT b FROM Book b WHERE :tag MEMBER OF b.tags ")
    // List<Book> findByTag2(@Param("tag") Tag tag);

}
