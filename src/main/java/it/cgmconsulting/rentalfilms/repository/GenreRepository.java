package it.cgmconsulting.rentalfilms.repository;

import it.cgmconsulting.rentalfilms.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
