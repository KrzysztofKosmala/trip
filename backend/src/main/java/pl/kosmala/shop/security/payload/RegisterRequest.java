package pl.kosmala.shop.security.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kosmala.shop.security.entity.types.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest
{
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}