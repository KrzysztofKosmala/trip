package pl.kosmala.shop.fakeData;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import pl.kosmala.shop.admin.model.AdminTrip;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.model.Product;
import pl.kosmala.shop.common.model.ProductCurrency;
import pl.kosmala.shop.common.utils.SlugifyUtils;
import pl.kosmala.shop.trip.model.Trip;
import pl.kosmala.shop.common.model.TripDestination;
import pl.kosmala.shop.common.model.Review;

import java.math.BigDecimal;
import java.util.*;

public class ProductGenerator
{

    Faker faker = new Faker();

    Random random =new Random();



    public Trip generateTrip()
    {
        Trip trip = new Trip();

        trip.setDestination(TripDestination.AU);
        trip.setBasePrice(BigDecimal.valueOf(faker.number().numberBetween(700, 25000)));
        String name = faker.name().name();
        trip.setName(name);
        trip.setDesc(faker.lorem().sentence( 30));
        trip.setSlug(SlugifyUtils.slugifySlug(name));
        trip.setFullDesc(faker.lorem().sentence(20));
        trip.setCurrency(ProductCurrency.PLN);
        trip.setHouse(faker.bool().bool());
        trip.setSpa(true);
        trip.setFood(true);
        trip.setSlopNearby(true);
        trip.setWifi(true);
        trip.setApartment(true);
        return trip;
    }

    public List<Trip> trips(int howMany)
    {
        List<Trip> trips = new ArrayList<>();
        for (int i =0; i<howMany; i++)
        {
            trips.add(this.generateTrip());
        }

        return trips;
    }

    public AdminTrip generateAdminTrip()
    {
        AdminTrip trip = new AdminTrip();

        trip.setDestination(TripDestination.AU);
        trip.setBasePrice(BigDecimal.valueOf(faker.number().numberBetween(700, 25000)));
        String name = faker.name().name();
        trip.setName(name);
        trip.setDesc(faker.lorem().sentence( 30));
        trip.setSlug(SlugifyUtils.slugifySlug(name));
        trip.setFullDesc(faker.lorem().sentence(20));
        trip.setCurrency(ProductCurrency.PLN);
        trip.setHouse(faker.bool().bool());
        trip.setSpa(true);
        trip.setFood(true);
        trip.setSlopNearby(true);
        trip.setWifi(true);
        trip.setApartment(true);
        return trip;
    }

    public AdminTrip generateAdminTripWithImages(List<Image> allImages)
    {
        AdminTrip trip = new AdminTrip();
        List<Image> copyOf = new ArrayList<Image>(allImages);
        trip.setDestination(TripDestination.AU);
        trip.setBasePrice(BigDecimal.valueOf(faker.number().numberBetween(700, 25000)));
        String name = faker.name().name();
        trip.setName(name);
        trip.setDesc(faker.lorem().sentence( 30));
        trip.setSlug(SlugifyUtils.slugifySlug(name));
        trip.setFullDesc(faker.lorem().sentence(20));
        trip.setCurrency(ProductCurrency.PLN);
        trip.setHouse(faker.bool().bool());
        trip.setSpa(true);
        trip.setFood(true);
        trip.setSlopNearby(true);
        trip.setWifi(true);
        trip.setApartment(true);

        int howMany = random.nextInt(1,4);

        Set<Image> randomImagesSelected = new HashSet<>();
        for (int i = 0; i < howMany; i++)
        {
            int i1 = random.nextInt(copyOf.size());
            randomImagesSelected.add(copyOf.get(i1));
            copyOf.remove(copyOf.get(i1));
        }
        trip.setImages(randomImagesSelected);

        return trip;
    }


    public List<AdminTrip> adminTrips(int howMany)
    {
        List<AdminTrip> trips = new ArrayList<>();
        for (int i =0; i<howMany; i++)
        {
            trips.add(this.generateAdminTrip());
        }

        return trips;
    }

}
