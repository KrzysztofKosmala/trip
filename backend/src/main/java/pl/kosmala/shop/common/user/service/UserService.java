package pl.kosmala.shop.common.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.common.user.dto.UserDto;
import pl.kosmala.shop.common.user.entity.Gender;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.common.user.repository.UserRepository;
import pl.kosmala.shop.common.user.service.exception.NoValidPeselException;
import pl.kosmala.shop.common.user.service.exception.NoValidPhoneNumberException;
import pl.kosmala.shop.common.user.service.exception.NoValidPostalException;

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
                .street(userFromRepo.getStreet())
                .city(userFromRepo.getCity())
                .pesel(userFromRepo.getPesel())
                .postal(userFromRepo.getPostal())
                .phone(userFromRepo.getPhone())
                .gender(userFromRepo.getGender().getValue())
                .build();

        return build;
    }
    public User getUserByEmail(String email)
    {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public boolean checkIfUserExists(String email)
    {
        return userRepository.existsByEmail(email);
    }
    public UserDto updateDetails(User user, UserDto details)
    {
        User userFromRepo = userRepository.findByEmail(user.getEmail()).orElseThrow();

        userFromRepo.setFirstname(details.getFirstname());
        userFromRepo.setLastname(details.getLastname());
        userFromRepo.setEmail(details.getEmail());

        userFromRepo.setStreet(details.getStreet());
        userFromRepo.setCity(details.getCity());
        userFromRepo.setGender(Gender.valueOf(details.getGender()));

        if (!validatePesel( details.getPesel())) {
            throw new NoValidPeselException("Podany PESEL jest niepoprawny");
        }
        userFromRepo.setPesel(details.getPesel());
        if (!validatePostalCode( details.getPostal())) {
            throw new NoValidPostalException("Podany kod pocztowy jest niepoprawny");
        }
        userFromRepo.setPostal(details.getPostal());

        if (!validatePhoneNumber( details.getPhone())) {
            throw new NoValidPhoneNumberException("Podany numer telefonu jest niepoprawny");
        }
        userFromRepo.setPhone(details.getPhone());

        User saved = userRepository.save(userFromRepo);
        return UserDto.builder()
                .email(saved.getEmail())
                .firstname(saved.getFirstname())
                .lastname(saved.getLastname())
                .street(saved.getStreet())
                .city(saved.getCity())
                .pesel(saved.getPesel())
                .postal(saved.getPostal())
                .phone(saved.getPhone())
                .gender(saved.getGender().getValue())
                .build();
    }

    private boolean validatePostalCode(String postalCode) {
        return postalCode != null && postalCode.matches("\\d{2}-\\d{3}");
    }

    private boolean validatePesel(String pesel) {
        if (pesel == null || pesel.length() != 11 || !pesel.matches("\\d{11}")) {
            return false;
        }
        return true;
    }
    public boolean checkIfUserCanOrder(User user)
    {
        User userFromRepo = userRepository.findByEmail(user.getEmail()).orElseThrow();

        return isUserDataCompleteForOrder(userFromRepo);
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{9,11}");
    }
    private boolean isUserDataCompleteForOrder(User user)
    {
        return user.getCity() != null
                && user.getStreet() != null
                && user.getGender() != null
                && user.getPesel() != null
                && user.getPostal() != null
                && user.getPhone() != null;
    }
}

