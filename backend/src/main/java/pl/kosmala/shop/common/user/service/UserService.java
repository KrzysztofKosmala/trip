package pl.kosmala.shop.common.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.user.dto.UserDto;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.common.user.repository.UserRepository;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    public UserDto getDetails(User user)
    {
        User userFromRepo = userRepository.findByEmail(user.getEmail()).orElseThrow();

        UserDto build = UserDto.builder()
                .email(userFromRepo.getEmail())
                .firstname(userFromRepo.getFirstname())
                .lastname(userFromRepo.getLastname())
                .build();

        return build;
    }

    public UserDto updateDetails(User user, UserDto details)
    {
        User userFromRepo = userRepository.findByEmail(user.getEmail()).orElseThrow();

        userFromRepo.setFirstname(details.getFirstname());
        userFromRepo.setLastname(details.getLastname());
        userFromRepo.setEmail(details.getEmail());

        User saved = userRepository.save(userFromRepo);
        return UserDto.builder().firstname(saved.getFirstname()).lastname(saved.getLastname()).email(saved.getEmail()).build();
    }
}

