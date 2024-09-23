package net.oceandepth.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.oceandepth.journalApp.entity.User;
import net.oceandepth.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveEntry(User user) {

        try {
            userRepository.save(user);
        } catch(Exception e) {
            log.error("Exception", e);
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

}
