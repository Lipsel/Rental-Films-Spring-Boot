package it.cgmconsulting.rentalfilms.controller;


import it.cgmconsulting.rentalfilms.payload.request.FilmRequest;
import it.cgmconsulting.rentalfilms.service.FilmService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PutMapping("/update-film/{filmId}")
    public ResponseEntity<?> updateFilm(@RequestBody @Valid FilmRequest request, @PathVariable @Min(1) int filmId) {
        return filmService.updateFilm(request, filmId);
    }

    @GetMapping("/find-films-by-language/{languageId}")
    public ResponseEntity<?> getFilmsByLanguageId(@PathVariable @Min(1) long languageId) {
        return filmService.findFilmsByLanguageId(languageId);
    }

    @PostMapping("/add-film-to-store/{storeId}/{filmId}")
    public ResponseEntity<?> addFilmToStore(
            @PathVariable @Min(1) long storeId,
            @PathVariable @Min(1) long filmId
    ) {
        return filmService.addFilmToStore(storeId, filmId);
    }

    @GetMapping("find-film-with-max-number-of-rent")
    public ResponseEntity<?> getFilmsWithMaxRent() {
        return filmService.getFilmsWithMaxRent();
    }

    //10
    @GetMapping("/find-rentable-films")
    public ResponseEntity<?> getRentableFilms(@RequestParam @Valid @NotBlank String title) {
        return filmService.findRentableFilms(title);
    }


    @GetMapping("/find-films-by-actors")
    public ResponseEntity<?> findFilmsByActors(@RequestParam("staffIds") Collection<Long> staffIds) {
        return filmService.findFilmsByActors(staffIds);
    }


}
