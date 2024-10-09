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
        emailService.sendEmail("22cs008@students.muet.edu.pk",
                "Testing Java mail sender",
                "mr. karan, make your deeds good enough to be praised, bcz you are our respected cr...");
    }

}
