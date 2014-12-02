package info.keik.bookmanager.service;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import info.keik.bookmanager.Application;
import info.keik.bookmanager.domain.BooksRepository;
import info.keik.bookmanager.domain.CommentsRepository;
import info.keik.bookmanager.model.Book;
import info.keik.bookmanager.model.Comment;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class CommentsServiceTest extends
        AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PersistenceContext
    EntityManager em;

    @Autowired
    CommentsService sut;

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    CommentsRepository commentsRepository;

    @Before
    public void setup() {
        jdbcTemplate.execute("TRUNCATE SCHEMA public AND COMMIT");

        Book book1 = new Book("t1", "a1", "p1");
        Book book2 = new Book("t2", "a2", "p2");

        Comment comment1 = new Comment("c1");
        Comment comment2 = new Comment("c2");
        Comment comment3 = new Comment("c3");

        book1.addComment(comment1);
        book1.addComment(comment2);
        book2.addComment(comment3);

        booksRepository.save(book1);
        booksRepository.save(book2);
        commentsRepository.save(comment1);
        commentsRepository.save(comment2);
        commentsRepository.save(comment3);
    }

    @Test
    public void コメントを追加できる() {
        // setup
        Book book1 = booksRepository.findByTitleContaining("t1").get(0);
        int count = book1.getComments().size();

        assertThat(count, is(2));
        // exercise
        Comment comment = new Comment("the comment");
        sut.addCommentToBook(book1.getId(), comment);
        commentsRepository.save(comment);

        em.flush();
        em.clear();

        // verify
        Book updatedBook1 = booksRepository.findByTitleContaining("t1").get(0);
        assertThat(updatedBook1.getComments(), hasSize(count + 1));
    }

    @Test
    public void コメントを更新できる() {
        // setup
        Book book1 = booksRepository.findByTitleContaining("t1").get(0);
        Comment comment2 = book1.getComments().get(1);
        // exercise
        comment2.setContent("fixed comment");
        sut.updateComment(comment2);

        em.flush();
        em.clear();

        // verify
        Book updatedBook1 = booksRepository.findByTitleContaining("t1").get(0);
        Comment updatedComment2 = updatedBook1.getComments().get(1);
        assertThat(updatedComment2.getContent(), is("fixed comment"));
    }

    @Test
    public void IDで指定したコメントを削除できる() {
        // setup
        Book book1 = booksRepository.findByTitleContaining("t1").get(0);
        int count = book1.getComments().size();
        Integer comment2_id = book1.getComments().get(1).getId();
        // exercise
        sut.deleteComment(comment2_id);

        em.flush();
        em.clear();

        // verify
        Book updatedBook1 = booksRepository.findByTitleContaining("t1").get(0);
        assertThat(updatedBook1.getComments(), hasSize(count - 1));
    }

    @Test
    public void 複数のIDで指定したコメントを削除できる() {
        // setup
        Book book1 = booksRepository.findByTitleContaining("t1").get(0);
        int count = book1.getComments().size();
        Integer comment1_id = book1.getComments().get(0).getId();
        Integer comment2_id = book1.getComments().get(1).getId();
        List<Integer> ids = Arrays.asList(comment1_id, comment2_id);
        // exercise
        sut.deleteComments(ids);

        em.flush();
        em.clear();

        // verify
        Book updatedBook1 = booksRepository.findByTitleContaining("t1").get(0);
        assertThat(updatedBook1.getComments(), hasSize(count - 2));
    }

}
