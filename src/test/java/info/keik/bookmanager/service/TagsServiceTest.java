package info.keik.bookmanager.service;

import static org.junit.Assert.fail;
import info.keik.bookmanager.Application;
import info.keik.bookmanager.domain.BooksRepository;
import info.keik.bookmanager.domain.TagsRepository;

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

        fail();
    }

    @Test
    public void 本に新規タグを追加できる() {
        fail();
    }

    @Test
    public void 本に既存タグを追加できる() {
        fail();
    }

    @Test
    public void 本に同じタグを追加できない() {
        fail();
    }

    @Test
    public void 本からタグを削除できる() {
        fail();
    }

    @Test
    public void すべてのタグを取得できる() {
        fail();
    }

}
