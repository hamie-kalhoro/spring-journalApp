package net.oceandepth.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail() {
        emailService.sendEmail("shahrayarsahito2@gmail.com",
                           "Testing Java mail sender",
                             "mama, pronounciation sikh...");
    }

}
