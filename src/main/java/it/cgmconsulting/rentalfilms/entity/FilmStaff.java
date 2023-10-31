package it.cgmconsulting.rentalfilms.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "film_staff")
public class FilmStaff {

    @Id
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Id
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmStaff filmStaff = (FilmStaff) o;
        return Objects.equals(film, filmStaff.film) && Objects.equals(staff, filmStaff.staff) && Objects.equals(role, filmStaff.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film, staff, role);
    }
}
