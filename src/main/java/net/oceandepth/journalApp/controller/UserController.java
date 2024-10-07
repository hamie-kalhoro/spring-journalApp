package net.oceandepth.journalApp.controller;

import net.oceandepth.journalApp.api.response.WeatherResponse;
import net.oceandepth.journalApp.entity.User;
import net.oceandepth.journalApp.repository.UserRepository;
import net.oceandepth.journalApp.service.UserService;
import net.oceandepth.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;
    UserRepository userRepository;
    WeatherService weatherService;
    @Autowired
    public UserController(UserService userService,
                          UserRepository userRepository,
                          WeatherService weatherService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.weatherService = weatherService;
    }

    @PutMapping
    public ResponseEntity<User> updateUser( @RequestBody User user ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userRepository.deleteByUserName(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings() {
        WeatherResponse weatherResponse = weatherService.getWeather("Sukkur");
        String greetings = "";
        if(weatherResponse != null) {
            greetings = ", Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("hi "+authentication.getName() + greetings, HttpStatus.OK);
    }

}
