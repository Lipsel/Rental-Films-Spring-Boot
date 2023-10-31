package it.cgmconsulting.rentalfilms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "rental")
public class Rental {

    @EmbeddedId
    private RentalId id;

    @ManyToOne
    @JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id", insertable = false, updatable = false)
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", insertable = false, updatable = false)
    private Customer customer;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime rental_return;

    public Rental(RentalId rentalId) {
    }
}
