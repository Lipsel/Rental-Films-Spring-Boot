package it.cgmconsulting.rentalfilms.repository;

import it.cgmconsulting.rentalfilms.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("SELECT i FROM Inventory i WHERE i.film.id = :filmId AND i NOT IN (SELECT r.inventory FROM Rental r WHERE r.inventory.film.id = :filmId)")
    Inventory findNotRentedFilmInventory(Long filmId);
}
