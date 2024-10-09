package net.oceandepth.journalApp.scheduler;

import net.oceandepth.journalApp.entity.User;
import net.oceandepth.journalApp.repository.UserRepositoryImpl;
import net.oceandepth.journalApp.service.EmailService;
import net.oceandepth.journalApp.service.SentimentAnalysisService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    EmailService emailService;
    UserRepositoryImpl userRepository;
    SentimentAnalysisService sentimentAnalysisService;
    public UserScheduler( EmailService emailService,
                          UserRepositoryImpl userRepository,
                          SentimentAnalysisService sentimentAnalysisService ) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.sentimentAnalysisService = sentimentAnalysisService;
    }


//    @Scheduled(cron = "0 0 9 ? * SUN")
    @Scheduled(cron = "0 * * ? * *")
    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepository.getUserForSA();
        for (User user : users) {
            List<String> filteredEntries = user.getJournalEntries().stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minusDays(7)))
                    .map(x -> x.getContent())
                    .collect(Collectors.toList());
            String entry = String.join("", filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", sentiment);
        }

    }

}
