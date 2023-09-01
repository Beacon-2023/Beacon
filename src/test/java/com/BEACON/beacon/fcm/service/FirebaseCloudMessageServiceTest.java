package com.BEACON.beacon.fcm.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FirebaseCloudMessageServiceTest {


    @Autowired
    private FirebaseCloudMessageService firebaseCloudMessageService;



    @Test
    void pushTest() throws IOException {
        firebaseCloudMessageService.sendMessageTo("targertToken","titleTest","bodyTest");
    }

}
