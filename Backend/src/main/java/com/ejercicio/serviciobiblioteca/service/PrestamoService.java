package com.ejercicio.serviciobiblioteca.service;

import com.ejercicio.serviciobiblioteca.model.Prestamo;
import com.ejercicio.serviciobiblioteca.repository.IprestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoService implements IprestamoService{

    @Autowired
    private IprestamoRepository prestamoRepo;

    @Override
    public List<Prestamo> getPrestamos() {
        return prestamoRepo.findAll();
    }

    @Override
    public void savePrestamo(Prestamo prestamo) {

        prestamoRepo.save(prestamo);
    }

    @Override
    public Prestamo editPrestamo(Long id, Prestamo prestamo) {

        Prestamo p = this.findPrestamo(id);
        p.setFechaPrestamo(prestamo.getFechaPrestamo());
        p.setFechaDevolucion(prestamo.getFechaDevolucion());
        p.setLibros(prestamo.getLibros());

        return prestamoRepo.save(p);
    }

    @Override
    public void deletePrestamo(Long id) {

        prestamoRepo.deleteById(id);

    }

    @Override
    public Prestamo findPrestamo(Long id) {

      return prestamoRepo.findById(id).orElse(null);

    }
}
