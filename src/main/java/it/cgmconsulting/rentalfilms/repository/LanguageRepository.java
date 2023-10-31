package it.cgmconsulting.rentalfilms.repository;

import it.cgmconsulting.rentalfilms.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

}
