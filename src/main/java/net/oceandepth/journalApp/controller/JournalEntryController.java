package net.oceandepth.journalApp.controller;

import net.oceandepth.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class JournalEntryController {

    private final Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry journalEntry) {
        journalEntries.put(journalEntry.getId(), journalEntry);
        return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry findStudentById(@PathVariable Long myId) {
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{journalId}")
    public JournalEntry removeStudentById(@PathVariable Long journalId) {
        return journalEntries.remove(journalId);
    }

    @PutMapping
    public JournalEntry updateJournalEntry(@PathVariable Long myid, @RequestBody JournalEntry journalEntry) {
        return journalEntries.put(myid, journalEntry);
    }

}
