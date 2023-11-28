package com.ejercicio.serviciobiblioteca.controller;

import com.ejercicio.serviciobiblioteca.model.Libro;
import com.ejercicio.serviciobiblioteca.service.IlibroService;
import jakarta.validation.Valid;
import org.springframework.validation.ObjectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")  // Reemplaza con la URL de tu aplicación frontend
@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private IlibroService libroServi;

    @GetMapping("/x")
    public String hola(){
        return "hola desde java";
    }

    //Trer libros
    @GetMapping("/traer")
    public List<Libro> getLibros(){

        return libroServi.getLibros();
    }

    //Crear libro
    @PostMapping("/crear")

    public ResponseEntity<?> saveLibro(@RequestBody Libro libro){

        libroServi.saveLibro(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Libro creado correctamente");

    }

    //Editar
    @PutMapping("/editar/{idLibro}")
    public Libro editLibro(@PathVariable Long idLibro, @RequestBody Libro libro){

        libroServi.editLibro(idLibro, libro);

        Libro l = libroServi.findLibro(idLibro);
        return l;
    }

    @DeleteMapping("/borrar/{idLibro}")
    public String deleteLibro(@PathVariable Long idLibro){
        libroServi.deleteLibro(idLibro);

        return "Libro eliminado correctamente";
    }

    @GetMapping("/traerXid/{idLibro}")
    public Libro getLibro(@PathVariable Long idLibro){

        return libroServi.findLibro(idLibro);
    }

    //buscar por titulo
    @GetMapping("/traerXtitulo/{titulo}")
    public Libro getLibroXTitulo(@PathVariable String titulo){

        return libroServi.getLibroXtitulo(titulo);
    }


}
