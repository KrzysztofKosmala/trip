package pl.kosmala.shop.common.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender
{
    MALE("Mężczyzna"), FEMALE("Kobieta");
    private String value;
}
