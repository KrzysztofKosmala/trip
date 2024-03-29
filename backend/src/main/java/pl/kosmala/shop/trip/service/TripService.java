package pl.kosmala.shop.trip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.trip.dto.TripDto;
import pl.kosmala.shop.trip.model.Trip;
import pl.kosmala.shop.common.model.TripDestination;
import pl.kosmala.shop.trip.repository.TripRepository;
import pl.kosmala.shop.review.dto.ReviewDto;
import pl.kosmala.shop.common.model.Review;

import java.util.List;

@Service
@RequiredArgsConstructor
public class  TripService
{
    @Autowired
    private final TripRepository tripRepository;


    public Page<Trip> getAllTrips(Pageable pageable)
    {
        return tripRepository.findAllActive(pageable);
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
                        .salePrice(tripBySlug.getSalePrice())
                        .currency(tripBySlug.getCurrency())
                        .slug(tripBySlug.getSlug())
                        .fullDesc(tripBySlug.getFullDesc())
                        .spa(tripBySlug.getSpa())
                        .apartment(tripBySlug.getApartment())
                        .house(tripBySlug.getHouse())
                        .wifi(tripBySlug.getWifi())
                        .food(tripBySlug.getFood())
                        .reviews(reviewsDto)
                        .images(tripBySlug.getImages())
                        .build();
    }

    public Page<Trip> getTripsByFilter(Pageable pageable, TripDestination destination, Boolean slopNearby, Boolean apartment, Boolean house, Boolean wifi, Boolean food, Boolean spa) {
        return tripRepository.findByFilter(pageable, destination, slopNearby, apartment, house, wifi, food, spa);
    }
}
