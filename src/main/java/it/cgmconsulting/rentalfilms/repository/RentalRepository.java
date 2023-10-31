package it.cgmconsulting.rentalfilms.repository;

import it.cgmconsulting.rentalfilms.entity.Rental;
import it.cgmconsulting.rentalfilms.entity.RentalId;
import it.cgmconsulting.rentalfilms.payload.response.FilmRentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, RentalId> {

    //6)
    @Query("SELECT COUNT(r) " +
            "FROM Rental r " +
            "JOIN r.inventory i " +
            "WHERE i.store.store_id = :store_id " +
            "AND ((r.id.rental_date BETWEEN :startDate AND :endDate) OR r.rental_return IS NULL)")
    Long countRentalsByStoreAndTime(Long store_id, LocalDateTime startDate, LocalDateTime endDate);


    //7) FilmRentResponse
    @Query("SELECT new it.cgmconsulting.rentalfilms.payload.response.FilmRentResponse(" +
            "r.inventory.film.film_id," +
            "r.inventory.film.title," +
            "r.inventory.store.store_name) " +
            "FROM Rental r " +
            "WHERE r.customer.customer_id = :customerId")
    List<FilmRentResponse> findFilmsRentByCustomer(Long customerId);





}
