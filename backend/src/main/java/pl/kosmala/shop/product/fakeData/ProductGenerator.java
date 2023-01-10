package pl.kosmala.shop.product.fakeData;

import com.github.javafaker.Faker;
import pl.kosmala.shop.product.model.Product;
import pl.kosmala.shop.product.trip.model.Trip;

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
        product.setCurrency(faker.currency().name());
        return product;
    }

    public Trip generateTrip()
    {
        Trip trip = new Trip();

        trip.setDestination(faker.country().capital());
        trip.setBasePrice(BigDecimal.valueOf(faker.number().numberBetween(700, 25000)));
        trip.setName(faker.name().name());
        trip.setDesc(faker.lorem().sentence( 30));
        trip.setCurrency(faker.currency().name());
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
