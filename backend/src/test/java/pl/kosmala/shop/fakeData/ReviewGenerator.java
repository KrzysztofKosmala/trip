package pl.kosmala.shop.fakeData;

import com.github.javafaker.Faker;
import pl.kosmala.shop.common.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewGenerator
{
    Faker faker = new Faker();


    public List<Review> review(int howMany)
    {
        List<Review> reviews = new ArrayList<>();
        for (int i =0; i<50; i++)
        {
            reviews.add(this.generateReview());
        }

        return reviews;
    }

    private Review generateReview()
    {
        Review review = new Review();
        review.setProductId(Long.valueOf(faker.number().numberBetween(1,10)));
        review.setContent(faker.lorem().sentence(15));
        review.setAuthorName(faker.name().fullName());
        return review;
    }
}
