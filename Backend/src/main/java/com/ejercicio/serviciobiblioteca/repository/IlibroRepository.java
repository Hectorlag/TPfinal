package com.ejercicio.serviciobiblioteca.repository;

import com.ejercicio.serviciobiblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IlibroRepository extends JpaRepository<Libro, Long> {

    // Método personalizado para buscar un libro por título
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    Libro findByTitulo(@Param("titulo") String titulo);
}
