package pl.kosmala.shop.fakeData;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.model.TripDestination;
import pl.kosmala.shop.trip.model.Trip;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static pl.kosmala.shop.common.utils.SlugifyUtils.slugifySlug;

@RequiredArgsConstructor
public class ImageGenerator
{
    @Value("${FETCH_IMAGE_URL}")
            private String fetchImage;
    Faker faker = new Faker();

    public Image generateImage()
    {
        Image model = new Image();
        String name = slugifySlug(faker.animal().name());

        model.setThumbImage(fetchImage+name);
        model.setName(name);
        model.setDesc(faker.lorem().sentence());
        model.setLocation(TripDestination.AU);
        model.setType(faker.artist().name());

        return model;

    }


    public List<Image> generateImages(int howMany)
    {
        List<Image> images = new ArrayList<>();
        for (int i =0; i<howMany; i++)
        {
            images.add(this.generateImage());
        }

        return images;
    }
}
