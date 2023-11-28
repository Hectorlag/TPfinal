package com.ejercicio.serviciobiblioteca.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrestamo;

    @NotNull(message = "La fecha de préstamo es obligatoria.")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaPrestamo;

    @Temporal(TemporalType.DATE)
    private LocalDate fechaDevolucion;

@ManyToMany
@JoinTable(
            name = "rela_libro_prestamo",
            joinColumns = @JoinColumn (name = "id_Prestamo", nullable = false),
            inverseJoinColumns = @JoinColumn (name = "id_Libro", nullable = false)
    )

private List<Libro> libros;

}
