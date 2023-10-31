package it.cgmconsulting.rentalfilms.repository;

import it.cgmconsulting.rentalfilms.entity.Film;
import it.cgmconsulting.rentalfilms.payload.response.FilmMaxRentResponse;
import it.cgmconsulting.rentalfilms.payload.response.FilmRentableResponse;
import it.cgmconsulting.rentalfilms.payload.response.FilmResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    Optional<Film> findById(long id);

    @Query("SELECT new it.cgmconsulting.rentalfilms.payload.response.FilmResponse(" +
            "f.film_id," +
            "f.title," +
            "f.description," +
            "f.release_year," +
            "f.language.language_name)" +
            "FROM Film f WHERE f.language.language_id = :languageId")
    List<FilmResponse> findFilmsByLanguage(long languageId);

    // 8)
    @Query("SELECT NEW it.cgmconsulting.rentalfilms.payload.response.FilmMaxRentResponse(" +
            "f.film_id," +
            "f.title, COUNT(r)) " +
            "FROM Film f " +
            "LEFT JOIN Rental r ON f.film_id = r.inventory.film.film_id " +
            "GROUP BY f.film_id, f.title " +
            "ORDER BY COUNT(r) DESC")
    List<FilmMaxRentResponse> findFilmWithMaxRent();

    //10)
    @Query("SELECT NEW it.cgmconsulting.rentalfilms.payload.response.FilmRentableResponse(" +
            "f.title, s.store_name, COUNT(i), (COUNT(i) - COUNT(r))) " +
            "FROM Film f " +
            "JOIN Inventory i ON f.film_id = i.film.film_id " +
            "JOIN Store s ON i.store.store_id = s.store_id " +
            "LEFT JOIN Rental r ON i.inventory_id = r.inventory.inventory_id " +
            "WHERE f.title = :title " +
            "GROUP BY f.title, s.store_name")
    List<FilmRentableResponse> findRentableFilms(String title);

    boolean existsByTitle(String title);

    //9)
    @Query("SELECT NEW it.cgmconsulting.rentalfilms.payload.response.FilmResponse(" +
            "f.film_id," +
            "f.title," +
            "f.description," +
            "f.release_year," +
            "l.language_name) " +
            "FROM Film f " +
            "JOIN f.language l " +
            "WHERE f.film_id IN " +
            "(SELECT fs.film.film_id FROM FilmStaff fs " +
            "WHERE fs.staff.staff_id IN :staffIds " +
            "GROUP BY fs.film.film_id HAVING COUNT(DISTINCT fs.staff.staff_id) = :count) " +
            "ORDER BY f.title")
    List<FilmResponse> findFilmsByActors(Collection<Long> staffIds, Long count);


}





