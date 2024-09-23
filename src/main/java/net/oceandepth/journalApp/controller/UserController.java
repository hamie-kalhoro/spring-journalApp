package net.oceandepth.journalApp.controller;

import net.oceandepth.journalApp.entity.JournalEntry;
import net.oceandepth.journalApp.entity.User;
import net.oceandepth.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping
    public ResponseEntity<User> createEntry(@RequestBody User userEntry) {

        try {
            userService.saveEntry(userEntry);
            return new ResponseEntity<>(userEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{entryId}")
    public ResponseEntity<User> getJournalEntryById(@PathVariable ObjectId entryId) {
        Optional<User> journalEntry = userService.findById(entryId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("id/{journalId}")
    public ResponseEntity<JournalEntry> removeStudentById(@PathVariable ObjectId journalId) {
        userService.deleteById(journalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<User> updateUser(
                                        @PathVariable String userName,
                                        @RequestBody User user) {
        User userInDb = userService.findByUserName(userName);
        if(userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
            return new ResponseEntity<>(userInDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
