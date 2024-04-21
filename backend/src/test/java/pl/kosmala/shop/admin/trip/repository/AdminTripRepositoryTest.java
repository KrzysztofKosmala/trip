package pl.kosmala.shop.admin.trip.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.kosmala.shop.admin.trip.model.AdminTrip;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import pl.kosmala.shop.common.model.ProductCurrency;
import pl.kosmala.shop.common.model.TripDestination;
@DataJpaTest
public class AdminTripRepositoryTest
{

    @Autowired
    private AdminTripRepository adminTripRepository;

    private AdminTrip adminTrip;

    @BeforeEach
    public void setUp()
    {
        adminTrip = AdminTrip.builder()
                .name("Test Trip")
                .desc("Test Description")
                .currency(ProductCurrency.PLN)
                .slug("test-trip")
                .fullDesc("Test Full Description")
                .destination(TripDestination.FR)
                .basePrice(new BigDecimal("1000.00"))
                .salePrice(new BigDecimal("800.00"))
                .slopNearby(true)
                .apartment(true)
                .house(false)
                .wifi(true)
                .food(true)
                .spa(false)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(7))
                .build();

        adminTripRepository.save(adminTrip);
    }

    @AfterEach
    public void tearDown()
    {
        adminTripRepository.deleteAll();
    }

    @Test
    public void testFindAdminTripBySlugWhenSlugExistsThenReturnAdminTrip()
    {
        Optional<AdminTrip> foundAdminTrip = adminTripRepository.findAdminTripBySlug("test-trip");

        assertTrue(foundAdminTrip.isPresent());
        assertEquals(adminTrip, foundAdminTrip.get());
    }

    @Test
    public void testFindAdminTripBySlugWhenSlugDoesNotExistThenReturnEmptyOptional()
    {
        Optional<AdminTrip> foundAdminTrip = adminTripRepository.findAdminTripBySlug("non-existing-slug");

        assertFalse(foundAdminTrip.isPresent());
    }

    @Test
    public void testFindAdminTripBySlugWhenSlugNotExistsThenReturnEmptyOptional()
    {
        // Act
        Optional<AdminTrip> found = adminTripRepository.findAdminTripBySlug("non-existing-slug");

        // Assert
        assertThat(found).isNotPresent();
    }
}