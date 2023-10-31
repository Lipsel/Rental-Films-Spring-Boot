package it.cgmconsulting.rentalfilms.repository;

import it.cgmconsulting.rentalfilms.entity.Customer;
import it.cgmconsulting.rentalfilms.payload.response.CustomerStoreResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


        // 4)
        @Query("SELECT NEW it.cgmconsulting.rentalfilms.payload.response.CustomerStoreResponse(" +
                "s.store_name," +
                "COUNT(DISTINCT r.customer)) " +
                "FROM Rental r " +
                "INNER JOIN r.inventory i " +
                "INNER JOIN i.store s " +
                "WHERE s.store_name = :storeName")
        List<CustomerStoreResponse> countCustomersByStore(String storeName);


}

