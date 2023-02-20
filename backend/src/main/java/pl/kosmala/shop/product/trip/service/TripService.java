package pl.kosmala.shop.product.trip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.product.trip.dto.TripDto;
import pl.kosmala.shop.product.trip.model.Trip;
import pl.kosmala.shop.product.trip.model.TripDestination;
import pl.kosmala.shop.product.trip.repository.TripRepository;
import pl.kosmala.shop.review.dto.ReviewDto;
import pl.kosmala.shop.common.model.Review;

import java.util.List;

@Service
public class TripService
{
    @Autowired
    TripRepository tripRepository;


    public Page<Trip> getAllTrips(Pageable pageable)
    {
        return tripRepository.findAll(pageable);
    }

    public TripDto getTripBySlug(String slug)
    {
        Trip tripBySlug = tripRepository.findTripBySlug(slug).orElseThrow();
        List<Review> reviews = tripBySlug.getReviews();
        List<ReviewDto> reviewsDto = reviews.stream().map(review ->
                new ReviewDto(review.getAuthorName(), review.getContent(), review.getProductId())).toList();

        return TripDto.builder()
                        .name(tripBySlug.getName())
                        .destination(tripBySlug.getDestination())
                        .desc(tripBySlug.getDesc())
                        .basePrice(tripBySlug.getBasePrice())
                        .currency(tripBySlug.getCurrency())
                        .slug(tripBySlug.getSlug())
                        .fullDesc(tripBySlug.getFullDesc())
                        .spa(tripBySlug.getSpa())
                        .apartment(tripBySlug.getApartment())
                        .house(tripBySlug.getHouse())
                        .wifi(tripBySlug.getWifi())
                        .food(tripBySlug.getFood())
                        .reviews(reviewsDto)
                        .build();
    }

    public Page<Trip> getTripsByFilter(Pageable pageable, TripDestination destination, Boolean slopNearby, Boolean apartment, Boolean house, Boolean wifi, Boolean food, Boolean spa) {
        return tripRepository.findByFilter(pageable, destination, slopNearby, apartment, house, wifi, food, spa);
    }
}
