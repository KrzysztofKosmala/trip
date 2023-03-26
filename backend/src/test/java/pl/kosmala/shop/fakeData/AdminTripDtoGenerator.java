package pl.kosmala.shop.fakeData;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import pl.kosmala.shop.admin.dto.AdminTripDto;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.model.ProductCurrency;
import pl.kosmala.shop.common.model.TripDestination;
import pl.kosmala.shop.common.utils.SlugifyUtils;
import pl.kosmala.shop.trip.model.Trip;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdminTripDtoGenerator
{

    Faker faker = new Faker();
    Random random = new Random();



    public AdminTripDto generateAdminTripDtp(List<Image> allImages)
    {
        List<Image> copyOf = new ArrayList<Image>(allImages);

        AdminTripDto trip = new AdminTripDto();

        trip.setDestination(TripDestination.AU);
        trip.setBasePrice(BigDecimal.valueOf(faker.number().numberBetween(700, 25000)));
        String name = faker.name().name();
        trip.setName(name);
        trip.setDesc(faker.lorem().sentence( 30));
        trip.setSlug(SlugifyUtils.slugifySlug(name));
        trip.setFullDesc(faker.lorem().sentence(20));
        trip.setCurrency(ProductCurrency.PLN);
        trip.setHouse(faker.bool().bool());
        trip.setSpa(faker.bool().bool());
        trip.setFood(faker.bool().bool());
        trip.setSlopNearby(faker.bool().bool());
        trip.setWifi(faker.bool().bool());
        trip.setApartment(faker.bool().bool());


        int howMany = random.nextInt(1,4);

        Image[] randomIds = new Image[howMany];
        for (int i = 0; i < howMany; i++)
        {
            int i1 = random.nextInt(copyOf.size());
            Image image = new Image();
            image.setId(copyOf.get(i1).getId());
            randomIds[i] = image;
            copyOf.remove(copyOf.get(i1));
        }
        trip.setImages(randomIds);
        return trip;
    }
}
