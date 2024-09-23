package net.oceandepth.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.oceandepth.journalApp.entity.JournalEntry;
import net.oceandepth.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {

    JournalEntryRepository journalEntryRepository;
    @Autowired
    public JournalEntryService(JournalEntryRepository journalEntryRepository) {
        this.journalEntryRepository = journalEntryRepository;
    }

    public void saveEntry(JournalEntry journalEntry) {

        try {
            journalEntry.setDate(LocalDateTime.now());
            journalEntryRepository.save(journalEntry);
        } catch(Exception e) {
            log.error("Exception", e);
        }
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }

}
