package pl.kosmala.shop.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.common.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class TripUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findById(Long.parseLong(username)).orElseThrow();
        return user;
    }
}
