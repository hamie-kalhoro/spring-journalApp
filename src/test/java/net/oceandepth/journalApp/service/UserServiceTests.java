package net.oceandepth.journalApp.service;

import net.oceandepth.journalApp.repository.UserRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @ParameterizedTest
    @ValueSource(strings = {
            "hamid",
            "sajid",
            "mudasir"
    })
    void testFindByUserName(String name) {
        assertNotNull(userRepository.findByUserName(name));
    }

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
