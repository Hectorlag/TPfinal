package com.ejercicio.serviciobiblioteca.service;


import com.ejercicio.serviciobiblioteca.model.Prestamo;

import java.util.List;

public interface IprestamoService {

    //Traer todos los prestamos
    public List<Prestamo> getPrestamos();

    //Crear
    public void savePrestamo(Prestamo prestamo);

    //Editar
    public Prestamo editPrestamo(Long id, Prestamo prestamo);

    //Borrar
    public void deletePrestamo(Long id);

    //Buscar
    public Prestamo findPrestamo(Long id);
}
