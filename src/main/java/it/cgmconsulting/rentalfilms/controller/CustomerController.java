package it.cgmconsulting.rentalfilms.controller;


import it.cgmconsulting.rentalfilms.service.CustomerService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/count-customers-by-store/{storeName}")
    public ResponseEntity<?> countCustomersByStoreName(@PathVariable @NotBlank String storeName) {
        return customerService.countCustomersByStore(storeName);
    }

}


