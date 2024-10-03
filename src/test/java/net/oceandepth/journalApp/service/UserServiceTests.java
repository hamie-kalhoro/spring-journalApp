package net.oceandepth.journalApp.service;

import net.oceandepth.journalApp.entity.User;
import net.oceandepth.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Disabled // I have used this to understand the ability of this annotation
    @Test
    void testSimpleAddition() {
        Assertions.assertEquals(44, 40+4);
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    void testSaveNewUser(User user) {
        assertTrue(userService.saveNewUser(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4",
            "3,3,6"
    })
    void testAdditionByParam(int a, int b, int result) {
        assertEquals(result, a+b);
    }

}
