package pl.kosmala.shop.product.fakeData;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import pl.kosmala.shop.product.model.Product;
import pl.kosmala.shop.product.model.ProductCurrency;
import pl.kosmala.shop.product.trip.model.Trip;
import pl.kosmala.shop.product.trip.model.TripDestination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductGenerator
{

    Faker faker = new Faker();

    public Product generateProduct()
    {
        Product product = new Product();
        product.setName(faker.name().name());
        product.setDesc(faker.lorem().characters(10, 300));
        product.setCurrency(ProductCurrency.PLN);
        return product;
    }

    public Trip generateTrip()
    {
        Trip trip = new Trip();

        trip.setDestination(TripDestination.AU);
        trip.setBasePrice(BigDecimal.valueOf(faker.number().numberBetween(700, 25000)));
        String name = faker.name().name();
        trip.setName(name);
        trip.setDesc(faker.lorem().sentence( 30));
        trip.setSlug(slugifySlug(name));
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

    private String slugifySlug(String slug)
    {
        final Slugify slg = Slugify.builder().customReplacement("_", "-").build();
        return slg.slugify(slug);
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




    public List<Product> products(int howMany)
    {
        List<Product> products = new ArrayList<>();
        for (int i =0; i<howMany; i++)
        {
            products.add(this.generateProduct());
        }

        return products;
    }
}
