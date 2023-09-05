package pl.kosmala.shop.fakeData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import pl.kosmala.shop.admin.trip.model.AdminTrip;
import pl.kosmala.shop.order.model.PaymentType;
import pl.kosmala.shop.order.model.dto.OrderDto;
import pl.kosmala.shop.common.user.entity.User;
import pl.kosmala.shop.order.repository.PaymentRepository;

import java.util.*;

public class OrderDtoGenerator
{

    private PaymentRepository paymentRepository;
    public OrderDto generateOrderDto(AdminTrip trip, User user)
    {
        return OrderDto.builder()
                .postal(user.getPostal())
                .productslug(trip.getSlug())
                .email(user.getEmail())
                .city(user.getCity())
                .phone(user.getPhone())
                .paymentId(1L)
                .street(user.getStreet())
                .lastname(user.getLastname())
                .firstname(user.getFirstname())
                .build();
    }

    public List<OrderDto> generateOrders(List<AdminTrip> trips, List<User> users, int howMany)
    {
        List<OrderDto> orderDtos = new ArrayList<>();

        if (trips.isEmpty() || users.isEmpty()) {
            return orderDtos; // Zwracamy pustą listę, jeśli nie ma dostępnych wycieczek lub użytkowników.
        }

        if (howMany <= 0) {
            return orderDtos; // Zwracamy pustą listę, jeśli howMany jest mniejsze lub równe 0.
        }

        Random random = new Random(); // Utwórz obiekt Random do losowania wycieczek i użytkowników.

        Set<String> orderedProducts = new HashSet<>(); // Zbiór przechowujący produkty, które użytkownik już zamówił.



        Map<String, Set<Long>> productUserOrdersMap = new HashMap<>(); // Mapa śledząca zamówienia użytkowników dla każdego produktu.

        while (orderDtos.size() < howMany) {
            AdminTrip trip = trips.get(random.nextInt(trips.size()));
            User user = users.get(random.nextInt(users.size()));
            String productSlug = trip.getSlug();
            Long userId = user.getId();

            Set<Long> userOrders = productUserOrdersMap.computeIfAbsent(productSlug, k -> new HashSet<>());

            if (userOrders.contains(userId)) {
                // Użytkownik już zamówił ten produkt, pomijamy to zamówienie.
                continue;
            }

            OrderDto orderDto = generateOrderDto(trip, user);
            orderDtos.add(orderDto);
            userOrders.add(userId);
        }

        return orderDtos;
    }
}
