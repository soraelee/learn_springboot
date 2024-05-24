package com.care.study01;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
@SpringBootTest
public class DataSourcesTests {

    @Autowired
    private DataSource dataSource;


    @Test
    public void testConnection() throws SQLException {
        @Cleanup Connection con = dataSource.getConnection();
//        log.info(con); //로그는 Log4j2에서 생성 - 에러가 나도 그냥 하면 됨
        Assertions.assertNotNull(con);
    }
}
