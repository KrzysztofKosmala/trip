package pl.kosmala.shop.security.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.security.entity.types.Role;
import pl.kosmala.shop.security.repository.UserRepository;
import pl.kosmala.shop.security.entity.User;
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

    public AuthenticationResponse register(RegisterRequest request)
    {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("Użytkownik o podanym adresie email już istnieje.");
        }
        // TODO: not safe - only for now
        Role role = request.getRole() == null ? Role.ROLE_CUSTOMER : request.getRole();

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        userRepository.save(user);
// TODO: can be only registration without authentication. I will change once I add sendig email confirmation
        return authenticate(AuthenticationRequest.builder().email(request.getEmail()).password(request.getPassword()).build());
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
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