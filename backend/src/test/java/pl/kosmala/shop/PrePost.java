package pl.kosmala.shop;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.transaction.annotation.Transactional;
import pl.kosmala.shop.admin.model.AdminTrip;
import pl.kosmala.shop.admin.repository.AdminTripRepository;
import pl.kosmala.shop.admin.service.AdminTripService;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.common.image.repository.ImageRepository;
import pl.kosmala.shop.common.image.service.ImageService;
import pl.kosmala.shop.fakeData.ImageGenerator;
import pl.kosmala.shop.fakeData.ProductGenerator;
import pl.kosmala.shop.trip.model.Trip;
import pl.kosmala.shop.trip.repository.TripRepository;

import java.util.List;
import java.util.Random;

public class PrePost
{

}
