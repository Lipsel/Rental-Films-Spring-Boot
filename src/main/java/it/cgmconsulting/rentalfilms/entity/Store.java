package it.cgmconsulting.rentalfilms.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long store_id;

    @Column(length = 60, nullable = false, unique = true)
    private String store_name;
}
