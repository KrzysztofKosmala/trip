package pl.kosmala.shop.security.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kosmala.shop.security.payload.*;
import pl.kosmala.shop.security.service.AccountConfirmationService;
import pl.kosmala.shop.security.service.AuthenticationService;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AccountConfirmationService accountConfirmationService;

    @PostMapping("/register")
    public AuthorizationResponse register(@RequestBody RegisterRequest request)
    {
        return authenticationService.register(request);
    }
    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request)
    {
        return authenticationService.authenticate(request);
    }
    @PostMapping("/confirmAccount")
    public void confirmAccount(@RequestBody ConfirmAccountRequest request)
    {
        accountConfirmationService.confirmAccount(request);
    }


}