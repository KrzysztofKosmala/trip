package pl.kosmala.shop.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import pl.kosmala.shop.admin.model.AdminTrip;
import pl.kosmala.shop.admin.repository.AdminTripRepository;
import pl.kosmala.shop.admin.service.AdminTripService;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.image.repository.ImageRepository;
import pl.kosmala.shop.common.image.service.ImageService;
import pl.kosmala.shop.fakeData.ImageGenerator;
import pl.kosmala.shop.fakeData.ProductGenerator;
import pl.kosmala.shop.trip.model.Trip;
import pl.kosmala.shop.trip.repository.TripRepository;

import java.util.List;
import java.util.Random;
import java.util.Set;

@DataJpaTest
@Transactional
@ActiveProfiles("test")
public class AdminTest
{

    @Autowired
    private AdminTripRepository adminTripRepository;

    @Autowired
    private TripRepository tripRepository;


    @Autowired
    ImageRepository imageRepository;

    private List<AdminTrip> testTrips;

    @BeforeEach void setup()
    {
        //dodać kilka tripów
        ProductGenerator productGenerator = new ProductGenerator();
        List<Trip> trips = productGenerator.trips(10);
        tripRepository.saveAll(trips);
        //dodać kilka images
        ImageGenerator imageGenerator =  new ImageGenerator();
        List<Image> images = imageGenerator.generateImages(30);
        imageRepository.saveAll(images);
        //połączyć ze sobą
        List<Image> allImages = imageRepository.findAll();
        List<Trip> allTrips = tripRepository.findAll();
        Random random = new Random();

        allTrips.forEach( trip ->
                {
                    int randomIndex = random.nextInt(allImages.size());
                    Image image = allImages.get(randomIndex);

                    trip.addImage(image);

                    tripRepository.save(trip);
                    imageRepository.save(image);
                }
        );



    }

    @Test
    void shouldCheckIfSetupWorksProperly()
    {
        // Sprawdzenie czy dane testowe zostały dodane do bazy danych
        List<Trip> tripsFromDb = tripRepository.findAll();
        List<Image> imagesFromDb = imageRepository.findAll();

        Assertions.assertEquals(10, tripsFromDb.size());
        Assertions.assertEquals(30, imagesFromDb.size());

        // Sprawdzenie czy każda wycieczka ma przypisane losowe zdjęcie
        for (Trip trip : tripsFromDb) {
            Set<Image> tripImages = trip.getImages();
            Assertions.assertEquals(1, tripImages.size());
        }

    }

}

