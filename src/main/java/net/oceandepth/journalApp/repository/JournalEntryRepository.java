package net.oceandepth.journalApp.repository;

import net.oceandepth.journalApp.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, Long> {
}
