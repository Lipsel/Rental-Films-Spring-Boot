package it.cgmconsulting.rentalfilms.service;

import it.cgmconsulting.rentalfilms.entity.*;
import it.cgmconsulting.rentalfilms.exception.ResourceNotFoundException;
import it.cgmconsulting.rentalfilms.payload.request.FilmRequest;
import it.cgmconsulting.rentalfilms.payload.response.FilmMaxRentResponse;
import it.cgmconsulting.rentalfilms.payload.response.FilmRentableResponse;
import it.cgmconsulting.rentalfilms.payload.response.FilmResponse;
import it.cgmconsulting.rentalfilms.repository.FilmRepository;
import it.cgmconsulting.rentalfilms.repository.InventoryRepository;
import it.cgmconsulting.rentalfilms.repository.StaffRepository;
import it.cgmconsulting.rentalfilms.repository.StoreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final StoreRepository storeRepository;
    private final InventoryRepository inventoryRepository;
    private final StaffRepository staffRepository;

    protected Film getFilmById(long filmId) {
        return filmRepository.findById(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("Film", "long", filmId));
    }

    protected Store getStoreById(long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("Store", "long", storeId));
    }

    @Transactional
    public ResponseEntity<?> updateFilm(FilmRequest request, long filmId) {
        Film film = getFilmById(filmId);
        film.setTitle(request.getTitle());
        film.setDescription(request.getDescription());
        film.setRelease_year(request.getRelease_year());
        return new ResponseEntity<>("Film "+film.getFilm_id()+" updated", HttpStatus.OK);
    }

    public ResponseEntity<?> findFilmsByLanguageId(long languageId) {
        List<FilmResponse> films = filmRepository.findFilmsByLanguage(languageId);

        if(films.isEmpty()) {
            return new ResponseEntity<>("Film non trovati con l'id di questa lingua", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> addFilmToStore(long storeId, long filmId) {

        Store store = getStoreById(storeId);

        Film film = getFilmById(filmId);

        // Aggiungi il film allo store
        Inventory inventory = new Inventory();
        inventory.setStore(store);
        inventory.setFilm(film);
        inventoryRepository.save(inventory);
        return ResponseEntity.ok("Film aggiunto al negozio con successo");
    }


    public ResponseEntity<?> getFilmsWithMaxRent() {
        List<FilmMaxRentResponse> filmsWithMaxRent = filmRepository.findFilmWithMaxRent();
        if (filmsWithMaxRent.isEmpty()) {
            return new ResponseEntity<>("Non è stato possibile ottenere i film", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(filmsWithMaxRent, HttpStatus.OK);
    }


    public ResponseEntity<?> findRentableFilms(String title) {
        //Verifico se il film esiste veramente dal titolo creando un metodo derivato
        boolean filmExistbyTitle = filmRepository.existsByTitle(title);

        if(!filmExistbyTitle) {
            return new ResponseEntity<>("Non è stato trovato nessun film con questo titolo", HttpStatus.NOT_FOUND);
        }

        List<FilmRentableResponse> filmRentableResponses = filmRepository.findRentableFilms(title);
        return new ResponseEntity<>(filmRentableResponses, HttpStatus.OK);

    }

    public ResponseEntity<?> findFilmsByActors(Collection<Long> staffIds) {

        // Verifica se gli id inseriti dello staff esistano veramente
        List<Staff> existingStaff = staffRepository.findAllById(staffIds);
        if (existingStaff.size() != staffIds.size()) {
            return new ResponseEntity<>("Alcuni ID dello staff non esistono", HttpStatus.BAD_REQUEST);
        }

        // Viene calcolato il numero totale di id inseriti nella variabile count
        Long count = (long) staffIds.size();
        List<FilmResponse> films = filmRepository.findFilmsByActors(staffIds, count);

        if (films.isEmpty()) {
            return new ResponseEntity<>("Questi attori non hanno lavorato insieme in nessun film", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(films, HttpStatus.OK);

    }

}
