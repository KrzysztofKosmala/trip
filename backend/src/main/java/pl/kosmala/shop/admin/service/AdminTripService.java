package pl.kosmala.shop.admin.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.admin.model.AdminTrip;
import pl.kosmala.shop.admin.repository.AdminTripRepository;

@Service
public class AdminTripService
{
    @Autowired
    AdminTripRepository adminTripRepository;
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

    public AdminTrip updateTrip(AdminTrip trip)
    {
        return adminTripRepository.save(trip);
    }

    public void deleteTrip(Long id)
    {
        adminTripRepository.deleteById(id);
    }
}
