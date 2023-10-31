package it.cgmconsulting.rentalfilms.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilmMaxRentResponse {
    private Long filmId;
    private String title;
    private Long totalRentCount;
}
