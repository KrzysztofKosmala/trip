package pl.kosmala.shop.admin.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.admin.dto.AdminTripDto;
import pl.kosmala.shop.admin.model.AdminTrip;
import pl.kosmala.shop.admin.repository.AdminTripRepository;
import pl.kosmala.shop.common.model.Image;

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

    public AdminTrip createTrip(AdminTrip trip)
    {
        return adminTripRepository.save(trip);
    }

    public AdminTrip updateTrip(AdminTripDto adminProductDto, Long id)
    {
        AdminTrip adminTrip = adminTripRepository.findById(id).orElseThrow();

        Set<Image> adminTripImages = adminTrip.getImages();

        // pobranie zbioru obiektów Image z bazy danych
        List<Long> imagesId = Arrays.stream(adminProductDto.getImages()).map(Image::getId).toList();
        Set<Image> images = imageService.findAllByIds(imagesId);

        // aktualizacja pól obiektu AdminTrip zgodnie z wartościami z DTO
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

// aktualizacja powiązań z obiektami Image
        adminTrip.getImages().removeIf(image -> !images.contains(image));
        images.forEach(image -> {
            if (!adminTrip.getImages().contains(image)) {
                adminTrip.getImages().add(image);
                image.addProduct(adminTrip);
            }
        });
        // zapis zmodyfikowanego obiektu AdminTrip do bazy danych
        return adminTripRepository.save(adminTrip);
    }

    public void deleteTrip(Long id)
    {
        Optional<AdminTrip> trip = adminTripRepository.findById(id);
        if(trip.isPresent())
        {
            trip.get().getImages().forEach(image -> image.removeProduct(trip.get()));
            adminTripRepository.deleteById(id);
        }
    }
}
