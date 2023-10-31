package it.cgmconsulting.rentalfilms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long language_id;

    @Column(length = 20, nullable = false, unique = true)
    private String language_name;
}
