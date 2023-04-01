package pl.kosmala.shop.order.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OrderDto
{
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String street;
    @NotBlank
    private String zipcode;
    @NotBlank
    private String city;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String productslug;
}
