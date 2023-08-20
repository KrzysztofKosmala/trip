package pl.kosmala.shop.common.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto
{
    private String firstname;
    private String lastname;
    private String email;

    private String phone;
    private String pesel;
    private String gender;
    private String street;
    private String postal;
    private String city;
}
