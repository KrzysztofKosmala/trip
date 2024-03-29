package pl.kosmala.shop.admin.trip.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import pl.kosmala.shop.admin.trip.dto.AdminTripDto;
import pl.kosmala.shop.admin.trip.model.AdminTrip;
import pl.kosmala.shop.admin.trip.repository.AdminTripRepository;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.image.repository.ImageRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static pl.kosmala.shop.common.utils.SlugifyUtils.slugifySlug;

@Service
@AllArgsConstructor
public class AdminTripService
{
    private final AdminTripRepository adminTripRepository;

    private final ImageRepository imageRepository;
    public AdminTrip getAdminTripBySlug(String slug)
    {
        Optional<AdminTrip> bySlug = adminTripRepository.findAdminTripBySlug(slug);

        return bySlug.orElseThrow();
    }

    public AdminTrip updateTrip(AdminTripDto adminProductDto, Long id)
    {

            AdminTrip adminTrip = adminTripRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("AdminTrip not found with id " + id));

            adminTrip.setName(adminProductDto.getName());
            adminTrip.setCurrency(adminProductDto.getCurrency());
            adminTrip.setBasePrice(adminProductDto.getBasePrice());
            adminTrip.setSalePrice(adminProductDto.getSalePrice());
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
            adminTrip.setStartDate(adminProductDto.getStartDate());
            adminTrip.setEndDate(adminProductDto.getEndDate());
            adminTrip.setShowOnHomePage(adminProductDto.getShowOnHomePage());
            List<Long> imagesIdFromRequest = Arrays.stream(adminProductDto.getImages()).map(Image::getId).toList();

            adminTrip.getImages().removeIf(imageAttachedToTheTrip -> !imagesIdFromRequest.contains(imageAttachedToTheTrip.getId()));

            Set<Image> chosenImages = imageRepository.findAllByIdIn(imagesIdFromRequest);
            chosenImages.forEach(image -> {
                if (!adminTrip.getImages().contains(image)) {
                    adminTrip.getImages().add(image);
                    image.addProduct(adminTrip);
                }
            });

            // zapis zmodyfikowanego obiektu AdminTrip do bazy danych
            return adminTripRepository.save(adminTrip);
    }


    public AdminTrip createTrip(AdminTripDto adminProductDto)
    {

        if(adminTripRepository.existsByName(adminProductDto.getName()))
        {
            throw new IllegalStateException(String.format("Name [%s] is taken", adminProductDto.getName()));
        }

        AdminTrip adminTrip = AdminTrip.builder()
                .name(adminProductDto.getName())
                .currency(adminProductDto.getCurrency())
                .basePrice(adminProductDto.getBasePrice())
                .salePrice(adminProductDto.getSalePrice())
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
                .endDate(adminProductDto.getEndDate())
                .startDate(adminProductDto.getStartDate())
                .build();

        adminTrip.setShowOnHomePage(adminTrip.getShowOnHomePage());

        List<Long> idsOfImageToBeAdd = Arrays.stream(adminProductDto.getImages()).map(Image::getId).toList();

        if(idsOfImageToBeAdd.size() > 0)
        {
            Set<Image> images = imageRepository.findAllWithProductsByIds(idsOfImageToBeAdd);
            if(adminProductDto.getImages().length != images.size())
            {
                throw new DataAccessResourceFailureException
                        (
                                String.format
                                        (
                                                "Cant add all expected images. You wanted to add [%s] images to the trip [%s] but only [%s] were in database - transaction abandoned!",
                                                adminProductDto.getImages().length,
                                                adminProductDto.getName(),
                                                images.size()
                                        )
                        );
            }
            images.forEach(image -> image.addProduct(adminTrip));
        }

        return adminTripRepository.save(adminTrip);
    }

    public Page<AdminTrip> getAllAdminTrips(Pageable pageable)
    {
        return adminTripRepository.findAllActive(pageable);
    }

    public AdminTrip getProduct(Long id)
    {
        return adminTripRepository.findById(id).orElseThrow();
    }

/*    public void deleteTrip(Long id)
    {
        Optional<AdminTrip> trip = adminTripRepository.findById(id);
        if(trip.isPresent())
        {
            if(trip.get().getImages() != null)
            {
                trip.get().getImages().forEach(image ->
                {
                    image.removeProduct(trip.get());
                    imageRepository.save(image);
                });
            }
            trip.get().detouchAllOrders();
            adminTripRepository.deleteById(id);
        }
    }*/
    public AdminTrip deactivateTrip(Long id)
    {
        AdminTrip adminTrip = adminTripRepository.findById(id).orElseThrow();

        adminTrip.setIsActive(false);

        return adminTripRepository.save(adminTrip);
    }
}
