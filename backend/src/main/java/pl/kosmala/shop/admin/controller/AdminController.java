package pl.kosmala.shop.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.EntityResponse;
import org.webjars.NotFoundException;
import pl.kosmala.shop.admin.dto.AdminTripDto;
import pl.kosmala.shop.admin.model.AdminTrip;
import pl.kosmala.shop.common.image.model.Image;
import pl.kosmala.shop.admin.service.AdminTripService;
import pl.kosmala.shop.common.image.service.ImageService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static pl.kosmala.shop.common.utils.SlugifyUtils.slugifySlug;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController
{
    private final AdminTripService adminTripService;

    @GetMapping("/trips")
    public Page<AdminTrip> getTrips(@PageableDefault(size = 30) Pageable pageable) { return adminTripService.getAllAdminTrips(pageable); }

    @GetMapping("/trips/{id}")
    public AdminTrip getTrip(@PathVariable Long id)
    {
        return adminTripService.getProduct(id);
    }

    @PostMapping("/trips")
    public AdminTrip addTrip(@RequestBody @Valid AdminTripDto adminProductDto)
    {
        try{
            return adminTripService.createTrip(adminProductDto);
        } catch (IllegalStateException illegalStateException)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, illegalStateException.getMessage());
        } catch (DataAccessResourceFailureException dataAccessResourceFailureException)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, dataAccessResourceFailureException.getMessage());
        }
    }

    @PutMapping("/trips/{id}")
    public AdminTrip updateTrip(@RequestBody @Valid AdminTripDto adminProductDto, @PathVariable Long id)
    {
        try{
            return adminTripService.updateTrip(adminProductDto, id);
        }catch (NotFoundException notFoundException)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, notFoundException.getMessage());
        }
    }

    @DeleteMapping("/trips/{id}")
    public void deleteTrip(@PathVariable Long id)
    {
        adminTripService.deleteTrip(id);
    }
}
