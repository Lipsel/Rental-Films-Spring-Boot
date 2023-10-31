package it.cgmconsulting.rentalfilms.service;

import it.cgmconsulting.rentalfilms.payload.response.FilmRentResponse;
import it.cgmconsulting.rentalfilms.repository.CustomerRepository;
import it.cgmconsulting.rentalfilms.repository.InventoryRepository;
import it.cgmconsulting.rentalfilms.repository.RentalRepository;
import it.cgmconsulting.rentalfilms.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;
    private final InventoryRepository inventoryRepository;


    public ResponseEntity<?> countRentalInDateRange(long storeId, LocalDateTime startDate, LocalDateTime endDate) {

        if(!storeRepository.existsById(storeId)) {
            return new ResponseEntity<>("Non ho trovato nessuno store con questo id", HttpStatus.NOT_FOUND);
        }

        if(startDate.isAfter(endDate)) {
            return new ResponseEntity<>("La data di inizio deve essere precedente alla data di fine.", HttpStatus.BAD_REQUEST);
        }

        if (endDate.isAfter(LocalDateTime.now())) {
            return new ResponseEntity<>("La data di fine non può essere del futuro", HttpStatus.BAD_REQUEST);
        }
        Long rentalCount = rentalRepository.countRentalsByStoreAndTime(storeId, startDate, endDate);

        return new ResponseEntity<>("In questo store sono stati noleggiati "+ rentalCount + " film", HttpStatus.OK);
    }

    public ResponseEntity<?> findAllFilmsRentByOne(long customerId) {
        List<FilmRentResponse> filmRentList = rentalRepository.findFilmsRentByCustomer(customerId);

        if (!customerRepository.existsById(customerId)) {
            return new ResponseEntity<>("Non ho trovato nessun cliente con questo id", HttpStatus.BAD_REQUEST);
        }

        if (filmRentList.isEmpty()) {
            return new ResponseEntity<>("Non ho trovato nessun noleggio", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(filmRentList, HttpStatus.OK);


    }
    /*
    public ResponseEntity<?> addOrUpdateRental(AddRentalRequest request, LocalDateTime rentalReturnDate) {
        //Estrae l'id dalla chiave composita
        RentalId rentalId = new RentalId(request.getCustomer_id(), request.getInventory_id(), request.getRental_date());
        Optional<Rental> existingRental = rentalRepository.findById(rentalId);
        //Se il noleggio esiste poi verifichiamo se il film sia ancora noleggiabile
        if(existingRental.isPresent()) {
            Inventory inventory = inventoryRepository.findNotRentedFilmInventory(request.getInventory_id());

            if (inventory == null) {
                return new ResponseEntity<>("Il Film non è noleggiabile", HttpStatus.BAD_REQUEST);
            }

            Rental rental = existingRental.get();
            rental.setRental_return(rentalReturnDate);
            rentalRepository.save(rental);

            return new ResponseEntity<>("Data di restituzione del noleggio aggiornato con successo", HttpStatus.OK);
        }

     */
    }


