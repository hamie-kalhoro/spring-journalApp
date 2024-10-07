package net.oceandepth.journalApp.controller;

import net.oceandepth.journalApp.cache.AppCache;
import net.oceandepth.journalApp.entity.User;
import net.oceandepth.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    UserService userService;
    AppCache appCache;
    @Autowired
    public AdminController(UserService userService, AppCache appCache) {
        this.userService = userService;
        this.appCache = appCache;
    }

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userService.getAll();
        if(all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody User newUser) {
        userService.saveAdmin(newUser);
    }

    @GetMapping("/clear-app-cache")
    public void clearAppCache() {
        appCache.init();
    }

}
