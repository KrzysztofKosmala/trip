package pl.kosmala.shop.IT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.image.repository.ImageRepository;
import pl.kosmala.shop.fakeData.ImageGenerator;
import pl.kosmala.shop.fakeData.ProductGenerator;
import pl.kosmala.shop.trip.model.Trip;
import pl.kosmala.shop.trip.repository.TripRepository;

import java.util.List;

public class Relation extends PrePost
{
    @Autowired
    private TripRepository tripRepository;

    @Test
    void test()
    {
        ProductGenerator productGenerator = new ProductGenerator();
        Trip trip = productGenerator.generateTrip();

        long count = tripRepository.count();
        Assertions.assertEquals(0, count);

        tripRepository.save(trip);

        count = tripRepository.count();

        Assertions.assertEquals(1, count);
    }
}
