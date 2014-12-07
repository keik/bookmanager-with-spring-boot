package info.keik.bookmanager.service;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import info.keik.bookmanager.Application;
import info.keik.bookmanager.domain.BooksRepository;
import info.keik.bookmanager.domain.TagsRepository;
import info.keik.bookmanager.model.Tag;
import info.keik.bookmanager.model.item.Book;

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
public class TagsServiceTest extends
        AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PersistenceContext
    EntityManager em;

    @Autowired
    TagsService sut;

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    TagsRepository tagsRepository;

    @Before
    public void setup() {
        jdbcTemplate.execute("TRUNCATE SCHEMA public AND COMMIT");

        // ----- setup data -----
        // book1 -> tag1, tag2
        // book2 -> tag3
        // book3 ->
        // ----------------------

        Book book1 = new Book("t1", "a1", "p1");
        Book book2 = new Book("t2", "a2", "p2");
        Book book3 = new Book("t3", "a3", "p3");

        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag2");
        Tag tag3 = new Tag("tag3");

        book1.addTag(tag1);
        book1.addTag(tag2);
        book2.addTag(tag3);

        booksRepository.save(book1);
        booksRepository.save(book2);
        booksRepository.save(book3);
        tagsRepository.save(tag1);
        tagsRepository.save(tag2);
        tagsRepository.save(tag3);
    }

    @Test
    public void 本に新規タグを追加できる() {
        // setup
        Book book1 = booksRepository.findByNameContaining("t1").get(0);
        Tag tag4 = new Tag("tag4");
        int count_tags_with_book1 = book1.getTags().size();
        int count_tags_all = (int) tagsRepository.count();
        // exercise
        Tag added = sut.addTagToItem(book1.getId(), tag4);

        em.flush();
        em.close();

        // verify
        Book updatedBook1 = booksRepository.findByNameContaining("t1").get(0);
        assertThat(updatedBook1.getTags(), hasSize(count_tags_with_book1 + 1));
        assertThat(tagsRepository.findAll(), hasSize(count_tags_all + 1));
        assertThat(added.getName(), is("tag4"));
    }

    @Test
    public void 本に既存タグを追加できる() {
        // setup
        Book book1 = booksRepository.findByNameContaining("t1").get(0);
        Tag tag3 = tagsRepository.findByName("tag3");
        int count_tags_with_book1 = book1.getTags().size();
        int count_tags_all = (int) tagsRepository.count();
        // exercise
        Tag added = sut.addTagToItem(book1.getId(), tag3);

        em.flush();
        em.close();

        // verify
        Book updatedBook1 = booksRepository.findByNameContaining("t1").get(0);
        assertThat(updatedBook1.getTags(), hasSize(count_tags_with_book1 + 1));
        assertThat(tagsRepository.findAll(), hasSize(count_tags_all));
        assertThat(added.getName(), is("tag3"));
    }

    @Test
    public void 本に同じタグを追加できない() {
        // setup
        Book book1 = booksRepository.findByNameContaining("t1").get(0);
        Tag tag1 = tagsRepository.findByName("tag1");
        int count_tags_with_book1 = book1.getTags().size();
        int count_tags_all = (int) tagsRepository.count();
        // exercise
        Tag added = sut.addTagToItem(book1.getId(), tag1);

        em.flush();
        em.close();

        // verify
        Book updatedBook1 = booksRepository.findByNameContaining("t1").get(0);
        assertThat(updatedBook1.getTags(), hasSize(count_tags_with_book1));
        assertThat(tagsRepository.findAll(), hasSize(count_tags_all));
        assertThat(added, is(nullValue()));
    }

    @Test
    public void 本からタグを削除できる() {
        // setup
        Book book1 = booksRepository.findByNameContaining("t1").get(0);
        Tag tag1 = tagsRepository.findByName("tag1");
        int count_tags_with_book1 = book1.getTags().size();
        int count_tags_all = (int) tagsRepository.count();
        // exercise
        Boolean isDeleted = sut.deleteTagFromItem(book1.getId(), tag1.getId());

        em.flush();
        em.close();

        // verify
        Book updatedBook1 = booksRepository.findByNameContaining("t1").get(0);
        assertThat(updatedBook1.getTags(), hasSize(count_tags_with_book1 - 1));
        assertThat(tagsRepository.findAll(), hasSize(count_tags_all));
        assertThat(isDeleted, is(true));
    }

    @Test
    public void 本に追加されていないタグを削除しようとして失敗してnullを返す() {
        // setup
        Book book1 = booksRepository.findByNameContaining("t1").get(0);
        Tag tag3 = tagsRepository.findByName("tag3");
        int count_tags_with_book1 = book1.getTags().size();
        int count_tags_all = (int) tagsRepository.count();
        // exercise
        Boolean isDeleted = sut.deleteTagFromItem(book1.getId(), tag3.getId());

        em.flush();
        em.close();

        // verify
        Book updatedBook1 = booksRepository.findByNameContaining("t1").get(0);
        assertThat(updatedBook1.getTags(), hasSize(count_tags_with_book1));
        assertThat(tagsRepository.findAll(), hasSize(count_tags_all));
        assertThat(isDeleted, is(false));
    }

    @Test
    public void すべてのタグを取得できる() {
        // setup
        // exercise
        // verify
        assertThat(sut.findAllTags(), hasSize((int) tagsRepository.count()));
    }
}
