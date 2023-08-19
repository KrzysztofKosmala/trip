package pl.kosmala.shop.common.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.user.dto.UserDto;
import pl.kosmala.shop.common.user.entity.Gender;
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
                .address(userFromRepo.getAddress())
                .city(userFromRepo.getCity())
                .pesel(userFromRepo.getPesel())
                .postal(userFromRepo.getPostal())
                .phone(userFromRepo.getPhone())
                .gender(userFromRepo.getGender().getValue())
                .build();

        return build;
    }

    public UserDto updateDetails(User user, UserDto details)
    {
        User userFromRepo = userRepository.findByEmail(user.getEmail()).orElseThrow();

        userFromRepo.setFirstname(details.getFirstname());
        userFromRepo.setLastname(details.getLastname());
        userFromRepo.setEmail(details.getEmail());

        userFromRepo.setAddress(details.getAddress());
        userFromRepo.setCity(details.getCity());
        userFromRepo.setGender(Gender.valueOf(details.getGender()));
        userFromRepo.setPesel(details.getPesel());
        userFromRepo.setPostal(details.getPostal());
        userFromRepo.setPhone(details.getPhone());

        User saved = userRepository.save(userFromRepo);
        return UserDto.builder()
                .email(saved.getEmail())
                .firstname(saved.getFirstname())
                .lastname(saved.getLastname())
                .address(saved.getAddress())
                .city(saved.getCity())
                .pesel(saved.getPesel())
                .postal(saved.getPostal())
                .phone(saved.getPhone())
                .gender(saved.getGender().getValue())
                .build();
    }

    public boolean checkIfUserCanOrder(User user)
    {
        User userFromRepo = userRepository.findByEmail(user.getEmail()).orElseThrow();

        return isUserDataCompleteForOrder(userFromRepo);
    }

    private boolean isUserDataCompleteForOrder(User user)
    {
        return user.getCity() != null
                && user.getAddress() != null
                && user.getGender() != null
                && user.getPesel() != null
                && user.getPostal() != null
                && user.getPhone() != null;
    }
}

