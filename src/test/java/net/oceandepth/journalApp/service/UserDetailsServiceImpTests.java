package net.oceandepth.journalApp.service;

import net.oceandepth.journalApp.entity.User;
import net.oceandepth.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

class UserDetailsServiceImpTests {

    @InjectMocks
    UserDetailsServiceImp userDetailsService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoadUserByUserName() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn(User.builder().userName("bilbo").password("hinfire")
                        .roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("bilbo");
        Assertions.assertNotNull(user);
    }

}
