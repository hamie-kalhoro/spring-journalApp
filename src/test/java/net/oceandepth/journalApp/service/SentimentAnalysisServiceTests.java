package net.oceandepth.journalApp.service;

import net.oceandepth.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SentimentAnalysisServiceTests {

    @Autowired
    UserScheduler userScheduler;

    @Test
    void testFetchUserAndSendEmail() {
        userScheduler.fetchUsersAndSendSaMail();
    }
}
