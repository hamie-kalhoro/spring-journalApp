package net.oceandepth.journalApp.service;

import net.oceandepth.journalApp.entity.User;
import net.oceandepth.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDetailsServiceImpTests {

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testLoadUserByUserName() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn(User.builder().userName("bilbo").password("falanadimkana")
                        .roles(new ArrayList<>()).build());
        UserDetails user = userDetailsServiceImp.loadUserByUsername("bilbo");
        assertNotNull(user);
    }

}
