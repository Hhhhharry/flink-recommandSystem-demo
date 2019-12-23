package com.demo;


import com.demo.client.HbaseClient;
import com.demo.service.UserScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class HbaseTest {

    @Autowired
    UserScoreService userScoreService;
    @Autowired
    private HbaseClient hbaseClient;

    @Test
    public void testHbase() throws IOException {
        userScoreService.calUserScore("1");
    }


    @Test
    public void testHbaseClient() throws IOException {
        String data = hbaseClient.getData("user", "1", "color", "red");
        System.out.println(data);
    }

    @Test
    public void testCreateTable() throws IOException {
        hbaseClient.createTable("test","test01");
    }
}
