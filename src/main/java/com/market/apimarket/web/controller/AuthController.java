package com.market.apimarket.web.controller;

import com.market.apimarket.domain.dto.AutenticationRequest;
import com.market.apimarket.domain.dto.AutenticationResponse;
import com.market.apimarket.domain.service.UserDetailsAppService;
import com.market.apimarket.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsAppService userDetailsAppService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/autenticate")
    public ResponseEntity<AutenticationResponse> createToken(@RequestBody AutenticationRequest request){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
            UserDetails userDetails = userDetailsAppService.loadUserByUsername(request.getUsername());
            String jwt= jwtUtil.generarToken(userDetails);
            return new ResponseEntity<>(new AutenticationResponse(jwt),HttpStatus.OK);
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
