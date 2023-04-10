package com.abernathy.mediscreen.mauthentication.service;

import com.abernathy.mediscreen.mauthentication.model.User;
import com.abernathy.mediscreen.mauthentication.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JpaUserDetailsServiceTest {

    @InjectMocks
    private JpaUserDetailsService serviceUnderTest;

    @Mock
    private UserRepository userRepository;

    @Test
    void loadUserByUsernameTest() {
        // Arrange
        User testUser = new User();
        testUser.setEmail(RandomString.make(64));
        testUser.setPassword(RandomString.make(64));
        testUser.setRole(RandomString.make(64));
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(testUser));

        // Act
        UserDetails result = serviceUnderTest.loadUserByUsername(testUser.getEmail());

        // Assert
        assertThat(result).isNotNull();
    }


}
