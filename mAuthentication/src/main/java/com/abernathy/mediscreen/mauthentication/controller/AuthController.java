package com.abernathy.mediscreen.mauthentication.controller;

import com.abernathy.mediscreen.mauthentication.dto.AuthRequestDto;
import com.abernathy.mediscreen.mauthentication.dto.AuthTokenDto;
import com.abernathy.mediscreen.mauthentication.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthController {

    private AuthService authService;
    private AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody AuthRequestDto authRequestDto) {
        log.info("register new user :" + authRequestDto.getUsername());
        return authService.createUser(authRequestDto);
    }

    @PostMapping("/token")
    public ResponseEntity<AuthTokenDto> getToken(@RequestBody AuthRequestDto authRequestDto) {
        log.info("getToken : " + authRequestDto.toString());

        String username = authRequestDto.getUsername();
        String password = authRequestDto.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        log.info("ok ? : " + authentication.isAuthenticated());
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(authService.generateToken(authRequestDto.getUsername()), HttpStatus.OK);
        } else {
            throw new RuntimeException("invalid access");
        }

    }

    @GetMapping("/")
    public String home() {
        return "Hello there !";
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }


}
