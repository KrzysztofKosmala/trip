package pl.kosmala.shop.security.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.kosmala.shop.security.payload.AuthenticationRequest;
import pl.kosmala.shop.security.payload.AuthenticationResponse;
import pl.kosmala.shop.security.service.AuthenticationService;
import pl.kosmala.shop.security.payload.RegisterRequest;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegisterRequest request)
    {
        return service.register(request);
    }
    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request)
    {
        return service.authenticate(request);
    }

    @PostMapping("/refresh-token")
    public void refreshToken
            (
                HttpServletRequest request,
                HttpServletResponse response
            ) throws IOException
    {
        service.refreshToken(request, response);
    }


}