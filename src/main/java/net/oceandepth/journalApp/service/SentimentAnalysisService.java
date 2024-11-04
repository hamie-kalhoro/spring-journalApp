package net.oceandepth.journalApp.service;

import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisService {

    public String getSentiment( String text ) {
        return "hello -> " +
                "    dear karan, did you insist mam to mark my attendance.";
    }

}
