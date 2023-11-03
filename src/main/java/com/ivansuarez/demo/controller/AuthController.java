package com.ivansuarez.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ivansuarez.demo.config.JwtUserDetailService;
import com.ivansuarez.demo.model.JWTRequest;
import com.ivansuarez.demo.model.JWTResponse;
import com.ivansuarez.demo.services.JWTService;

@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailService jwtUserDetailService;
    private final JWTService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
	public AuthController(AuthenticationManager authenticationManager, JwtUserDetailService jwtUserDetailService,
			JWTService jwtService) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUserDetailService = jwtUserDetailService;
		this.jwtService = jwtService;
	}

    @PostMapping("/authenticate")
    public ResponseEntity<?> postToken(@RequestBody JWTRequest request) {

        logger.debug("Authenticate endpoint");
        this.authenticate(request);
        //return null;
        final var userDetails = this.jwtUserDetailService.loadUserByUsername(request.getUsername());

        final var token = this.jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    private void authenticate(JWTRequest request) {
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException | DisabledException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
