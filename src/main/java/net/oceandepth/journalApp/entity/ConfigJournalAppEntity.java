package net.oceandepth.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_journal_app")
@Data
@Builder
public class ConfigJournalAppEntity {

    private String key;
    private String value;

}
