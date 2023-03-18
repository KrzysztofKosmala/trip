package pl.kosmala.shop.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;
import pl.kosmala.shop.admin.dto.AdminTripDto;
import pl.kosmala.shop.admin.model.AdminTrip;
import pl.kosmala.shop.admin.repository.AdminTripRepository;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.image.service.ImageService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static pl.kosmala.shop.common.utils.SlugifyUtils.slugifySlug;

@Service
public class AdminTripService
{
    @Autowired
    AdminTripRepository adminTripRepository;

    @Autowired
    ImageService imageService;
    public Page<AdminTrip> getAllAdminTrips(Pageable pageable)
    {
        return adminTripRepository.findAll(pageable);
    }

    public AdminTrip getProduct(Long id)
    {
        return adminTripRepository.findById(id).orElseThrow();
    }

    public AdminTrip createTrip(AdminTripDto adminProductDto)
    {

        List<Long> ids = Arrays.stream(adminProductDto.getImages()).map(Image::getId).toList();

        Set<Image> images = imageService.findAllByIds(ids);


        AdminTrip adminTrip = AdminTrip.builder()
                .name(adminProductDto.getName())
                .currency(adminProductDto.getCurrency())
                .basePrice(adminProductDto.getBasePrice())
                .desc(adminProductDto.getDesc())
                .destination(adminProductDto.getDestination())
                .slug(slugifySlug(adminProductDto.getSlug()))
                .fullDesc(adminProductDto.getFullDesc())
                .slopNearby(adminProductDto.getSlopNearby())
                .food(adminProductDto.getFood())
                .spa(adminProductDto.getSpa())
                .house(adminProductDto.getHouse())
                .wifi(adminProductDto.getWifi())
                .apartment(adminProductDto.getApartment())
                .build();

        images.forEach(image -> {
            image.addProduct(adminTrip);
        });


        return adminTripRepository.save(adminTrip);
    }

    public AdminTrip updateTrip(AdminTripDto adminProductDto, Long id)
    {
        try {
            AdminTrip adminTrip = adminTripRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("AdminTrip not found with id " + id));

            adminTrip.setName(adminProductDto.getName());
            adminTrip.setCurrency(adminProductDto.getCurrency());
            adminTrip.setBasePrice(adminProductDto.getBasePrice());
            adminTrip.setDesc(adminProductDto.getDesc());
            adminTrip.setDestination(adminProductDto.getDestination());
            adminTrip.setSlug(slugifySlug(adminProductDto.getSlug()));
            adminTrip.setFullDesc(adminProductDto.getFullDesc());
            adminTrip.setSlopNearby(adminProductDto.getSlopNearby());
            adminTrip.setFood(adminProductDto.getFood());
            adminTrip.setSpa(adminProductDto.getSpa());
            adminTrip.setHouse(adminProductDto.getHouse());
            adminTrip.setWifi(adminProductDto.getWifi());
            adminTrip.setApartment(adminProductDto.getApartment());

            List<Long> imagesIdFromRequest = Arrays.stream(adminProductDto.getImages()).map(Image::getId).toList();

            adminTrip.getImages().removeIf(imageAttachedToTheTrip -> !imagesIdFromRequest.contains(imageAttachedToTheTrip.getId()));

            Set<Image> chosenImages = imageService.findAllByIds(imagesIdFromRequest);
            chosenImages.forEach(image -> {
                if (!adminTrip.getImages().contains(image)) {
                    adminTrip.getImages().add(image);
                    image.addProduct(adminTrip);
                }
            });

            // zapis zmodyfikowanego obiektu AdminTrip do bazy danych
            return adminTripRepository.save(adminTrip);

        } catch (NotFoundException e) {
            // obsługa wyjątku NotFoundException
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public void deleteTrip(Long id)
    {
        Optional<AdminTrip> trip = adminTripRepository.findById(id);
        if(trip.isPresent())
        {
            trip.get().getImages().forEach(image -> {
                image.removeProduct(trip.get());
                imageService.saveFile(image);
            });
            adminTripRepository.deleteById(id);
        }
    }
}
