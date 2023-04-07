package com.abernathy.mediscreen.mauthentication.controller;

import com.abernathy.mediscreen.mauthentication.dto.AuthRequestDto;
import com.abernathy.mediscreen.mauthentication.dto.AuthTokenDto;
import com.abernathy.mediscreen.mauthentication.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import com.abernathy.mediscreen.mauthentication.exception.*;

@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody AuthRequestDto authRequestDto) throws UserAlreadyExistsException {
        log.info("Start registering new user :" + authRequestDto.getUsername());

        return new ResponseEntity<>(authService.createUser(authRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("/token")
    public ResponseEntity<AuthTokenDto> getToken(@RequestBody AuthRequestDto authRequestDto) throws InvalidAccessException {
        log.info("Token requested for : " + authRequestDto.getUsername());

        String username = authRequestDto.getUsername();
        String password = authRequestDto.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManager.authenticate(token);
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(authService.generateToken(authRequestDto.getUsername()), HttpStatus.OK);
        } else {
            throw new InvalidAccessException();
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        log.info("Start Token validation : token");
        authService.validateToken(token);
        return new ResponseEntity<>("Token is valid", HttpStatus.ACCEPTED);
    }


}
