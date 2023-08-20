package pl.kosmala.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kosmala.shop.order.model.Payment;
import pl.kosmala.shop.order.model.Transport;
import pl.kosmala.shop.order.repository.TransportRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportService
{
    private final TransportRepository transportRepository;

    public List<Transport> getTransports()
    {
        return transportRepository.findAll();
    }

}
