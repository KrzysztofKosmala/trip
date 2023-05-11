package pl.kosmala.shop.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/v1/login")
public class LoginController
{
    private final AuthenticationManager authenticationManager;

    private long expirationTime;

    private String secret;

    private UserRepository userRepository;

    public LoginController(AuthenticationManager authenticationManager, @Value("${jwt.expirationTime}") long expirationTime, @Value("${jwt.secret}") String secret, UserRepository userRepository)
    {
        this.authenticationManager = authenticationManager;
        this.expirationTime = expirationTime;
        this.secret = secret;
        this.userRepository = userRepository;
    }

    @PostMapping
    public Token login(@RequestBody LoginCredentials loginCredentials)
    {
        return authenticate(loginCredentials.getUsername(), loginCredentials.getPassword());
    }
    private Token authenticate(String username, String password) {
        User user = userRepository.findByEmail(username).orElseThrow();

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getId(), password)
        );
        TripUserDetails principal = (TripUserDetails) authenticate.getPrincipal();
        String token = JWT.create()
                .withSubject(String.valueOf(principal.getId()))
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));
        return new Token(token, principal.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .filter(s -> Role.ROLE_ADMIN.name().equals(s))
                .map(s -> true)
                .findFirst()
                .orElse(false));
    }

}
