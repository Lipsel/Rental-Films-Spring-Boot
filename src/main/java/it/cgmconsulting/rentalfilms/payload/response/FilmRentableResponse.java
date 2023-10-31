package it.cgmconsulting.rentalfilms.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilmRentableResponse {
    private String title;
    private String store_name;
    private long totalCopies;
    private long availableCopies;
}
