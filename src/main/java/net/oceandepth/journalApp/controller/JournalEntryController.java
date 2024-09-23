package net.oceandepth.journalApp.controller;

import net.oceandepth.journalApp.entity.JournalEntry;
import net.oceandepth.journalApp.entity.User;
import net.oceandepth.journalApp.service.JournalEntryService;
import net.oceandepth.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    JournalEntryService journalEntryService;
    UserService userService;
    @Autowired
    public JournalEntryController(JournalEntryService journalEntryService,
                                  UserService userService) {
        this.journalEntryService = journalEntryService;
        this.userService = userService;
    }

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(
            @PathVariable String userName) {
        User user = userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry
            ( @RequestBody JournalEntry myEntry,
              @PathVariable String userName ) {
        try {
            journalEntryService.saveEntry(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{entryId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId entryId) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(entryId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("id/{userName}/{journalId}")
    public ResponseEntity<JournalEntry> removeStudentById(
            @PathVariable ObjectId journalId,
            @PathVariable String userName ) {
        journalEntryService.deleteById(journalId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{userName}/{myId}")
    public ResponseEntity<JournalEntry> updateJournalEntry(
                                @PathVariable ObjectId myId,
                                @RequestBody JournalEntry newEntry,
                                @PathVariable String userName) {
        JournalEntry old = journalEntryService.findById(myId).orElse(null);
        if(old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
