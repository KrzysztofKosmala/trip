package pl.kosmala.shop.admin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessResourceFailureException;
import org.webjars.NotFoundException;
import pl.kosmala.shop.admin.trip.dto.AdminTripDto;
import pl.kosmala.shop.admin.trip.model.AdminTrip;
import pl.kosmala.shop.admin.trip.repository.AdminTripRepository;
import pl.kosmala.shop.admin.trip.service.AdminTripService;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.image.repository.ImageRepository;
import pl.kosmala.shop.fakeData.AdminTripDtoGenerator;
import pl.kosmala.shop.fakeData.ImageGenerator;
import pl.kosmala.shop.fakeData.ProductGenerator;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
@ExtendWith(MockitoExtension.class)
public class AdminTripServiceTest
{
    @Mock
    private AdminTripRepository adminTripRepository;

    @Mock
    private  ImageRepository imageRepository;

    private AdminTripService underTest;


    @Captor
    private ArgumentCaptor<AdminTrip> adminTripArgumentCaptor;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        underTest = new AdminTripService(adminTripRepository, imageRepository);
    }

    @Test
    void shouldCreateTrip()
    {
        AdminTripDtoGenerator generator = new AdminTripDtoGenerator();
        ImageGenerator imageGenerator = new ImageGenerator();
        List<Image> images = imageGenerator.generateImages(10);


        AtomicLong atomicLong = new AtomicLong();
        for (Image image : images)
            image.setId(atomicLong.getAndIncrement());

        AdminTripDto adminTripDto = generator.generateAdminTripDtp(images);

        given(adminTripRepository.existsByName(adminTripDto.getName())).willReturn(false);
        Image[] dtoImages = adminTripDto.getImages();
        List<Image> dtoImagesAsList = Arrays.asList(dtoImages);
        Set<Image> dtoImagesAsSet = new HashSet<>(dtoImagesAsList);
        given(imageRepository.findAllWithProductsByIds(anyList())).willReturn(dtoImagesAsSet);

        underTest.createTrip(adminTripDto);

        then(adminTripRepository).should().save(adminTripArgumentCaptor.capture());

        AdminTrip adminTripPassedToSave = adminTripArgumentCaptor.getValue();
        Assertions.assertEquals(adminTripDto.getName(), adminTripPassedToSave.getName());

        Set<Image> imagesFromAdminTripPassedToSave = adminTripPassedToSave.getImages();
        List<Image> imagesFromAdminTripPassedToSaveList = new ArrayList<>(imagesFromAdminTripPassedToSave);
        Collections.sort(imagesFromAdminTripPassedToSaveList);
        List<Image> imagesFromDto = Arrays.asList(dtoImages);
        Collections.sort(imagesFromDto);

        Assertions.assertEquals(imagesFromDto, imagesFromAdminTripPassedToSaveList);

    }

    @Test
    void shouldThrowIllegalStateException()
    {
        AdminTripDtoGenerator generator = new AdminTripDtoGenerator();
        ImageGenerator imageGenerator = new ImageGenerator();
        List<Image> images = imageGenerator.generateImages(10);


        AtomicLong atomicLong = new AtomicLong();
        for (Image image : images)
            image.setId(atomicLong.getAndIncrement());



        AdminTripDto adminTripDto = generator.generateAdminTripDtp(images);

        given(adminTripRepository.existsByName(adminTripDto.getName())).willReturn(true);

        Assertions.assertThrows(IllegalStateException.class ,() -> underTest.createTrip(adminTripDto), String.format("Name [%s] is taken", adminTripDto.getName()));
    }

    @Test
    void shouldThrowDataAccessResourceFailureException()
    {
        AdminTripDtoGenerator generator = new AdminTripDtoGenerator();
        ImageGenerator imageGenerator = new ImageGenerator();
        List<Image> generatedImages = imageGenerator.generateImages(10);


        AtomicLong atomicLong = new AtomicLong();
        for (Image image : generatedImages)
            image.setId(atomicLong.getAndIncrement());



        AdminTripDto adminTripDto = generator.generateAdminTripDtp(generatedImages);

        given(adminTripRepository.existsByName(adminTripDto.getName())).willReturn(false);

        HashSet<Image> images = new HashSet<>();
        given(imageRepository.findAllByIdIn(anyList())).willReturn(images);


        Assertions.assertThrows
                (
                        DataAccessResourceFailureException.class ,
                        () -> underTest.createTrip(adminTripDto),
                        String.format
                        (
                                "Cant add all expected images. You wanted to add [%s] images to the trip [%s] but only [%s] were in database - transaction abandoned!",
                                adminTripDto.getImages().length,
                                adminTripDto.getName(),
                                images.size()
                        )
                );
    }

    @Test
    void shouldUpdateTrip()
    {
        AdminTripDtoGenerator adminTripDtoGenerator = new AdminTripDtoGenerator();
        ImageGenerator imageGenerator = new ImageGenerator();
        ProductGenerator productGenerator = new ProductGenerator();
        List<Image> generatedImages = imageGenerator.generateImages(10);
        AtomicLong atomicLong = new AtomicLong();
        for (Image image : generatedImages)
            image.setId(atomicLong.getAndIncrement());
        AdminTrip tripToUpdate = productGenerator.generateAdminTripWithImages(generatedImages);
        long idOfTripToUpdate = atomicLong.getAndIncrement();
        tripToUpdate.setId(idOfTripToUpdate);
        AdminTripDto adminTripDto = adminTripDtoGenerator.generateAdminTripDtp(generatedImages);
        Image[] dtoImages = adminTripDto.getImages();

        Set<Image> fullImagesFromDto = generatedImages.stream()
                .filter(generatedImage -> Arrays.stream(dtoImages)
                        .anyMatch(dtoImage -> dtoImage.getId().equals(generatedImage.getId())))
                .collect(Collectors.toSet());

        given(imageRepository.findAllByIdIn(anyList())).willReturn(fullImagesFromDto);
        given(adminTripRepository.findById(idOfTripToUpdate)).willReturn(Optional.of(tripToUpdate));


        underTest.updateTrip(adminTripDto, idOfTripToUpdate);


        then(adminTripRepository).should().save(adminTripArgumentCaptor.capture());

        AdminTrip adminTripPassedToSave = adminTripArgumentCaptor.getValue();
        Assertions.assertEquals(adminTripDto.getName(), adminTripPassedToSave.getName());
        Assertions.assertEquals(idOfTripToUpdate, adminTripPassedToSave.getId());

        Set<Image> imagesFromAdminTripPassedToSave = adminTripPassedToSave.getImages();
        List<Image> imagesFromAdminTripPassedToSaveList = new ArrayList<>(imagesFromAdminTripPassedToSave);
        Collections.sort(imagesFromAdminTripPassedToSaveList);
        //List<Image> imagesFromDto = Arrays.asList(dtoImages);
        ArrayList<Image> fullImagesFromDtoList = new ArrayList<>(fullImagesFromDto);
        Collections.sort(fullImagesFromDtoList);

        Assertions.assertEquals(fullImagesFromDtoList, imagesFromAdminTripPassedToSaveList);

    }

    @Test
    void shouldThrowNotFoundExceptionWhenUpdatingTripThatInNoInDb()
    {
        AdminTripDtoGenerator adminTripDtoGenerator = new AdminTripDtoGenerator();
        ImageGenerator imageGenerator = new ImageGenerator();
        List<Image> generatedImages = imageGenerator.generateImages(30);
        AdminTripDto adminTripDto = adminTripDtoGenerator.generateAdminTripDtp(generatedImages);
        given(adminTripRepository.findById(anyLong())).willReturn(Optional.empty());
        long id = 1L;
        Assertions.assertThrows
                (
                        NotFoundException.class,
                        () -> underTest.updateTrip(adminTripDto, id),
                        "AdminTrip not found with id " + id
                );

    }
}
