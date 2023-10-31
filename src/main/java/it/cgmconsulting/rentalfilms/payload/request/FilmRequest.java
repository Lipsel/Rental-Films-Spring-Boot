package it.cgmconsulting.rentalfilms.payload.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class FilmRequest {

    @NotBlank @Size(min = 5, max = 120)
    private String title;

    @NotBlank @Size(min=50, max=255)
    private String description;

    @Min(1900) @Max(2023)
    private Short release_year;

    public String getTitle() {
        return title.trim();
    }

    public String getDescription() {
        return description.trim();
    }

    public Short getRelease_year() {
        return release_year;
    }
}
