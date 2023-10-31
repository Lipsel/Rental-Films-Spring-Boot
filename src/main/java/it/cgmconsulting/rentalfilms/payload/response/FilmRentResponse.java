package it.cgmconsulting.rentalfilms.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmRentResponse {
    private Long filmId;
    private String title;
    private String storeName;
}
