package info.keik.bookmanager.service;

import static org.junit.Assert.fail;
import info.keik.bookmanager.Application;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class UsersServiceTest extends
        AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    UsersService sut;

    @Before
    public void setup() {
        jdbcTemplate.execute("TRUNCATE SCHEMA public AND COMMIT");
    }

    @Test
    public void todo() {
        fail();
    }

}
