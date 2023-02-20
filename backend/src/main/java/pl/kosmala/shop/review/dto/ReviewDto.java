package pl.kosmala.shop.review.dto;

import org.hibernate.validator.constraints.Length;

public record ReviewDto(@Length(min = 2, max = 60) String authorName, @Length(min = 2, max = 500) String content, Long productId)
{

}
