package com.example.replicatest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplicationServiceTest {

    @Autowired
    private ReplicationService replicationService;

    @Autowired
    private TestService testService;

    @Test
    void 데이터베이스_URL() {
        replicationService.write();
        replicationService.read();
        Thread thread = new Thread();
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.get();
        stringThreadLocal.set("gd");
    }

    @Test
    void test() throws Exception {
        testService.connect();
    }

}