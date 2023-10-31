package it.cgmconsulting.rentalfilms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long genre_id;

    @Column(length = 30, nullable = false, unique = true)
    private String genre_name;
}
