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

}
