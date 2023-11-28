package com.ejercicio.serviciobiblioteca.controller;

import com.ejercicio.serviciobiblioteca.DTO.LibroDTO;
import com.ejercicio.serviciobiblioteca.DTO.PrestamoDTO;
import com.ejercicio.serviciobiblioteca.model.Prestamo;
import com.ejercicio.serviciobiblioteca.service.IprestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private IprestamoService prestamoServi;

    //Traer todos
    @GetMapping("/traer")
    public List<PrestamoDTO> getPrestamos() {


        List<Prestamo> prestamos = prestamoServi.getPrestamos();
        List<PrestamoDTO> prestamosDTO = new ArrayList<>();

        for (Prestamo p : prestamos) {
            PrestamoDTO prestamoDTO = new PrestamoDTO();
            prestamoDTO.setIdPrestamo(p.getIdPrestamo());
            prestamoDTO.setFechaPrestamo(p.getFechaPrestamo());
            prestamoDTO.setFechaDevolucion(p.getFechaDevolucion());

            // Obtener la lista de libros asociados al préstamo
            List<LibroDTO> librosDTO = p.getLibros().stream()//.stream()``**: Convierte la lista de libros en un flujo de elementos.
                    .map(libro -> new LibroDTO(libro.getIdLibro(), libro.getTitulo()))
                    .collect(Collectors.toList());

            //La función MAP toma un objeto Libro y crea un nuevo objeto LibroDTO utilizando
            // el constructor de LibroDTO que espera un id y un titulo.
            // En resumen, esta línea de código está transformando cada objeto Libro en un objeto LibroDTO.
            prestamoDTO.setLibros(librosDTO);


            prestamosDTO.add(prestamoDTO);
        }
        return prestamosDTO;
    }

    //Crear
    @PostMapping("/crear")
    public String savePrestamo(@RequestBody Prestamo prestamo){

        prestamoServi.savePrestamo(prestamo);
        return "Prestamo creado correctamente";
    }

    //Editar
    @PutMapping("/editar/{idPrestamo}")
    public Prestamo editPrestamo(@PathVariable Long idPrestamo, @RequestBody Prestamo prestamo){

        prestamoServi.editPrestamo(idPrestamo, prestamo);
        Prestamo p = prestamoServi.findPrestamo(idPrestamo);

        return p;
    }

    //Borrar
    @DeleteMapping("/borrar/{idPrestamo}")
    public String deletePrestamo(@PathVariable Long idPrestamo){

        prestamoServi.deletePrestamo(idPrestamo);

        return "Prestamo eliminado correctamnete";
    }

    @GetMapping("/traer/{idPrestamo}")
    public Prestamo traerXid(@PathVariable Long idPrestamo){

        return prestamoServi.findPrestamo(idPrestamo);

    }

}
