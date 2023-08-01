package pl.kosmala.shop.admin.homepage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HomePageProductStrategy
{
    BASIC("Predefiniowane");
    private String value;
}
