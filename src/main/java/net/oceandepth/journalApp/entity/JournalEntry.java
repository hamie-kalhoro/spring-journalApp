package net.oceandepth.journalApp.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data
public class JournalEntry {
    @Id
    // this annotation make the id field primary and ObjectId data-type is used by mongodb for creating ids'.
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;
}
