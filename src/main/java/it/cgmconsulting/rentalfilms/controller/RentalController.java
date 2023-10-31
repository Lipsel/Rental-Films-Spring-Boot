package it.cgmconsulting.rentalfilms.controller;

import it.cgmconsulting.rentalfilms.service.RentalService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @GetMapping("/count-rentals-in-date-range-by-store/{store_id}")
    public ResponseEntity<?> countRentalInDateRange(
            @PathVariable @Min(1) Long store_id,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate) {
        return rentalService.countRentalInDateRange(store_id, startDate, endDate);
    }

    @GetMapping("/find-all-films-rent-by-one-customer/{customerId}")
    public ResponseEntity<?> findAllFilmsRentByOne(@PathVariable @Min(1) long customerId) {
        return rentalService.findAllFilmsRentByOne(customerId);
    }




}
