package com.ejercicio.serviciobiblioteca.DTO;

import com.ejercicio.serviciobiblioteca.model.Libro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PrestamoDTO {

    private Long idPrestamo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private List<LibroDTO> libros;
}
