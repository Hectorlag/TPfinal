package com.ejercicio.serviciobiblioteca.service;

import com.ejercicio.serviciobiblioteca.model.Libro;

import java.util.List;

public interface IlibroService {

    //Traer libros
    public List<Libro> getLibros();

    //Crear libro
    public void saveLibro(Libro lIbro);

    //Editar
    public Libro editLibro(Long id, Libro libro);

    //Eliminar libro
    public void deleteLibro(Long id);

    //Buscar libro
    public Libro findLibro(Long id);

    public Libro getLibroXtitulo(String titulo);
}
