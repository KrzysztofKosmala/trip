package pl.kosmala.shop.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kosmala.shop.review.dto.ReviewDto;
import pl.kosmala.shop.common.model.Review;
import pl.kosmala.shop.review.service.ReviewService;

@RestController
@RequiredArgsConstructor
public class ReviewController
{
    private final ReviewService reviewService;

    @PostMapping
    public Review addReview(@RequestBody @Valid ReviewDto reviewDto)
    {
        Review review = Review.builder()
                .authorName(clearContent(reviewDto.authorName()))
                .content(clearContent(reviewDto.content()))
                .productId(reviewDto.productId()).build();
        return reviewService.addReview(review);
    }

    private String clearContent(String content)
    {
        return Jsoup.clean(content, Safelist.none());
    }
}
