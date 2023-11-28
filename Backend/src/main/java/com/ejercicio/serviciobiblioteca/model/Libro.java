package com.ejercicio.serviciobiblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;

    @NotBlank(message = "El nombre del libro no puede estar vacío")
    private String titulo;

    @NotBlank(message = "El nombre del autor no puede estar vacío")
    private String autor;

    @NotBlank(message = "El genero del libro no puede estar vacío")
    private String genero;

    private Integer cantPaginas;
    private String sinopsis;

    //La propiedad "libros" de la clase prestamo es la PROPIETARIA de la relacion
    @ManyToMany(mappedBy = "libros")

    @JsonIgnore
    private List<Prestamo> listaDePrestamos;

}
