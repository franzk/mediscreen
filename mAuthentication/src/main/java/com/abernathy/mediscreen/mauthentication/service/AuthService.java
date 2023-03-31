package com.abernathy.mediscreen.mauthentication.service;

import com.abernathy.mediscreen.mauthentication.dto.AuthRequestDto;
import com.abernathy.mediscreen.mauthentication.dto.AuthTokenDto;
import com.abernathy.mediscreen.mauthentication.model.User;
import com.abernathy.mediscreen.mauthentication.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthTokenDto generateToken(String username) {
        log.info("AuthService::generateToken for user " + username);
        AuthTokenDto authTokenDto = new AuthTokenDto();
        authTokenDto.setAuthToken(jwtService.generateToken(username));
        return authTokenDto;
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public String createUser(AuthRequestDto authRequestDto) {
        User user = new User();
        user.setEmail(authRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(authRequestDto.getPassword()));
        userRepository.save(user);
        return "user added !";
    }
}
