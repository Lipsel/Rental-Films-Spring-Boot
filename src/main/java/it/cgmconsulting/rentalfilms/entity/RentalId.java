package it.cgmconsulting.rentalfilms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public class RentalId implements Serializable {
        private Long customer_id;
        private Long inventory_id;
        @Column(columnDefinition = "DATETIME")
        private LocalDateTime rental_date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalId rentalId = (RentalId) o;
        return Objects.equals(customer_id, rentalId.customer_id) && Objects.equals(inventory_id, rentalId.inventory_id) && Objects.equals(rental_date, rentalId.rental_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer_id, inventory_id, rental_date);
    }
}
