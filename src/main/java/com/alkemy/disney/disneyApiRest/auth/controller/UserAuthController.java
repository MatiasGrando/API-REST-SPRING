package com.alkemy.disney.disneyApiRest.auth.controller;

import com.alkemy.disney.disneyApiRest.auth.dto.AuthenticationRequest;
import com.alkemy.disney.disneyApiRest.auth.dto.AuthenticationResponse;
import com.alkemy.disney.disneyApiRest.auth.dto.UserDTO;
import com.alkemy.disney.disneyApiRest.auth.service.JwtUtils;
import com.alkemy.disney.disneyApiRest.auth.service.UserAuthService;
import com.alkemy.disney.disneyApiRest.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtTokenUtil;
    @Autowired
    private UserAuthService userAuthService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDTO user) throws Exception{
        this.userDetailsCustomService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authRequest) throws Exception {

        return ResponseEntity.ok(new AuthenticationResponse(userAuthService.generateToken(authRequest)));
    }

}
