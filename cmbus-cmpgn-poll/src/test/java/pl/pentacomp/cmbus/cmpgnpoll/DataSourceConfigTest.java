package pl.pentacomp.cmbus.cmpgnpoll;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/spring/test-ds-context.xml")
public class DataSourceConfigTest {

  @Autowired
  private DataSource ds;

  @Test
  public void testBoneCP() throws SQLException {

    Connection connection = ds.getConnection();
    System.out.println(connection);
    connection.close();
  }
}

