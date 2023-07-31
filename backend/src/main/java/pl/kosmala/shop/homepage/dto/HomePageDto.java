package pl.kosmala.shop.homepage.dto;

import pl.kosmala.shop.common.model.Product;

import java.util.List;

public record HomePageDto(List<Product> products)
{
}
