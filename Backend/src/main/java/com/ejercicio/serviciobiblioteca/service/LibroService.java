package com.ejercicio.serviciobiblioteca.service;

import com.ejercicio.serviciobiblioteca.model.Libro;
import com.ejercicio.serviciobiblioteca.repository.IlibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService implements IlibroService{

    @Autowired
    private IlibroRepository libroRepo;

    @Override
    public List<Libro> getLibros() {
        return libroRepo.findAll();
    }

    @Override
    public void saveLibro(Libro libro) {
        libroRepo.save(libro);
    }

    @Override
    public Libro editLibro(Long id, Libro libro) {
        Libro l = this.findLibro(id);
        l.setAutor(libro.getAutor());
        l.setGenero(libro.getGenero());
        l.setTitulo(libro.getTitulo());
        l.setSinopsis(libro.getSinopsis());
        l.setCantPaginas(libro.getCantPaginas());
        l.setListaDePrestamos(libro.getListaDePrestamos());

        return libroRepo.save(l);
    }

    @Override
    public void deleteLibro(Long id) {
        libroRepo.deleteById(id);
    }

    @Override
    public Libro findLibro(Long id) {
        return libroRepo.findById(id).orElse(null);
    }

    @Override
    public Libro getLibroXtitulo(String titulo) {

        return libroRepo.findByTitulo(titulo);
    }
}
