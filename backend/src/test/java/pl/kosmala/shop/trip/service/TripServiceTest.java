package pl.kosmala.shop.trip.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kosmala.shop.admin.service.AdminTripService;
import pl.kosmala.shop.common.model.Review;
import pl.kosmala.shop.fakeData.ProductGenerator;
import pl.kosmala.shop.fakeData.ReviewGenerator;
import pl.kosmala.shop.review.dto.ReviewDto;
import pl.kosmala.shop.trip.dto.TripDto;
import pl.kosmala.shop.trip.model.Trip;
import pl.kosmala.shop.trip.repository.TripRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.testcontainers.shaded.org.awaitility.Awaitility.given;

@ExtendWith(MockitoExtension.class)
class TripServiceTest
{
    @Mock
    private TripRepository tripRepository;

    private TripService underTest;



    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        underTest = new TripService(tripRepository);
    }

    @Test
    void shouldGetTripBySlug()
    {
        ProductGenerator productGenerator = new ProductGenerator();

        ReviewGenerator reviewGenerator = new ReviewGenerator();
        List<Review> review = reviewGenerator.review(5);
        Trip trip = productGenerator.generateTrip();
        Optional<Trip> optionalObject = Optional.ofNullable(trip);
        trip.setReviews(review);

        BDDMockito.given(tripRepository.findTripBySlug("slug")).willReturn(optionalObject);

        TripDto tripDto = underTest.getTripBySlug("slug");

        Assertions.assertEquals(trip.getName(), tripDto.getName());
        Assertions.assertEquals(trip.getName(), tripDto.getName());
        Assertions.assertEquals(trip.getName(), tripDto.getName());
        Assertions.assertEquals(trip.getName(), tripDto.getName());

        List<ReviewDto> reviews = tripDto.getReviews();

        Assertions.assertEquals(review.size(), reviews.size());

        for(int i = 0; i < review.size(); i++)
        {
            review.get(i).getAuthorName().equals(reviews.get(i).authorName());
            review.get(i).getContent().equals(reviews.get(i).content());
        }

    }

}