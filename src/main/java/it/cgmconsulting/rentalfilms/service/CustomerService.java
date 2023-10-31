package it.cgmconsulting.rentalfilms.service;


import it.cgmconsulting.rentalfilms.entity.Store;
import it.cgmconsulting.rentalfilms.payload.response.CustomerStoreResponse;
import it.cgmconsulting.rentalfilms.repository.CustomerRepository;
import it.cgmconsulting.rentalfilms.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;


    public ResponseEntity<?> countCustomersByStore(String storeName) {

        Store store = storeRepository.findByStoreName(storeName);
        if (store == null) {
            return new ResponseEntity<>("Lo store non esiste", HttpStatus.NOT_FOUND);
        }
        List<CustomerStoreResponse> customers = customerRepository.countCustomersByStore(storeName);
        return new ResponseEntity<>(customers, HttpStatus.OK);


    }
}

