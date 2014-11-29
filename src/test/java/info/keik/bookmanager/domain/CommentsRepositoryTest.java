package info.keik.bookmanager.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import info.keik.bookmanager.Application;
import info.keik.bookmanager.model.Book;
import info.keik.bookmanager.model.Comment;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CommentsRepositoryTest extends
        AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    CommentsRepository sut;

    @Autowired
    BooksRepository booksRepository;

    @Test
    public void bookは得られないのですか() {
        // setup
        final Book book = new Book("aaa", "bbb", "ccc");
        booksRepository.save(book);
        Comment comment = new Comment();
        comment.setBook(book);
        comment.setContent("test comment");
        sut.save(comment);

        // exercise
        List<Comment> comments = sut.findAll();
        // verify
        assertThat(comments, hasSize(1));
        assertThat(comments.get(0).getBook(), is(not(nullValue())));
        assertThat(comments.get(0).getBook().getTitle(), is("aaa"));
        // teardown

    }

}
