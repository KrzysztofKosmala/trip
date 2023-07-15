package pl.kosmala.shop.security.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.user.entity.types.Role;
import pl.kosmala.shop.security.exception.RegisterException;
import pl.kosmala.shop.security.payload.AuthorizationResponse;
import pl.kosmala.shop.common.user.repository.UserRepository;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.security.payload.AuthenticationRequest;
import pl.kosmala.shop.security.payload.AuthenticationResponse;
import pl.kosmala.shop.security.payload.RegisterRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final AccountConfirmationService accountConfirmationService;

    public AuthorizationResponse register(RegisterRequest request)
    {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegisterException("Użytkownik o podanym adresie email już istnieje.");
        }
        // TODO: not safe - only for now
        Role role = request.getRole() == null ? Role.ROLE_CUSTOMER : request.getRole();

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .isEnabled(false)
                .build();

        userRepository.save(user);

        try{
            accountConfirmationService.sendEmailConfirmationLink(user.getEmail());
        }catch (RuntimeException ex){
            return new AuthorizationResponse(ex.getMessage());
        }

        return new AuthorizationResponse("Mail z linkiem aktywacyjnym został wysłany. Potwirdź swój adres email.");
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        if (!user.isEnabled()) {
            throw new IllegalStateException("Konto nie jeste aktywne.");
        }
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getId(),
                        request.getPassword()
                )
        );
        User principal = (User) authenticate.getPrincipal();
        var jwtToken = jwtService.generateToken(principal);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .adminAccess(principal.getAuthorities().stream().map(grantedAuthority -> grantedAuthority.getAuthority()).filter(s-> Role.ROLE_ADMIN.name().equals(s)).map(s -> true).findFirst().orElse(false))
                .build();
    }



}