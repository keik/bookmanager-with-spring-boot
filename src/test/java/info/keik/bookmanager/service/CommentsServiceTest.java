package info.keik.bookmanager.service;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import info.keik.bookmanager.Application;
import info.keik.bookmanager.domain.BooksRepository;
import info.keik.bookmanager.domain.CommentsRepository;
import info.keik.bookmanager.model.Book;
import info.keik.bookmanager.model.Comment;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Before;
import org.junit.Ignore;
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
@Transactional
public class CommentsServiceTest extends
        AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    CommentsService sut;

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    BooksService booksService;

    @Before
    public void setup() {
        deleteFromTables("books");
        deleteFromTables("comments");
    }

    @Test
    public void コメントを追加できる() {
        // setup
        Book book = new Book("aaa", "bbb", "ccc");
        booksRepository.save(book);
        // exercise
        Comment comment = new Comment();
        comment.setContent("the comment");
        sut.addCommentToBook(book.getId(), comment);
        // verify
        List<Comment> comments = commentsRepository.findAll();
        assertThat(comments, hasSize(1));
        assertThat(comments.get(0),
                is(SamePropertyValuesAs.samePropertyValuesAs(comment)));
        // teardown
    }

    @Test
    public void コメントを更新できる() {
        // setup
        Book book = new Book("aaa", "bbb", "ccc");
        booksRepository.save(book);
        Comment comment = new Comment();
        comment.setContent("the comment");
        sut.addCommentToBook(book.getId(), comment);
        // exercise
        Comment newcomment = new Comment();
        newcomment.setId(comment.getId());
        newcomment.setContent("fixed comment");
        sut.updateComment(newcomment);
        // verify
        System.out.println(book);
        System.out.println(booksRepository.findAll());
        System.out.println(booksService.findAllBooks());
        assertThat(book.getComments().get(0).getContent(), is("fixed comment"));
        // teardown
    }

    @Test
    @Ignore
    public void IDで指定したコメントを削除できる() {
        // setup
        Book book = new Book("aaa", "bbb", "ccc");
        booksRepository.save(book);
        Comment comment = new Comment();
        comment.setContent("the comment");
        sut.addCommentToBook(book.getId(), comment);
        // exercise
        sut.deleteComment(comment.getId());
        // verify
        List<Comment> comments = book.getComments();
        assertThat(comments, empty());
        // teardown
    }

    @Test
    @Ignore
    public void 複数のIDで指定したコメントを削除できる() {
        // setup
        Book book = new Book("aaa", "bbb", "ccc");
        booksRepository.save(book);
        Comment comment1 = new Comment();
        comment1.setContent("the comment");
        Comment comment2 = new Comment();
        comment2.setContent("the comment");
        Comment comment3 = new Comment();
        comment3.setContent("the comment");
        sut.addCommentToBook(book.getId(), comment1);
        sut.addCommentToBook(book.getId(), comment2);
        sut.addCommentToBook(book.getId(), comment3);
        // exercise
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(comment1.getId());
        ids.add(comment3.getId());
        sut.deleteComments(ids);
        List<Comment> comments = book.getComments();
        // verify
        assertThat(comments, is(hasSize(1)));
        // teardown
    }

}
