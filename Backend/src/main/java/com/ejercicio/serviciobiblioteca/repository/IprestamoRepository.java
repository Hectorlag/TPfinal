package com.ejercicio.serviciobiblioteca.repository;

import com.ejercicio.serviciobiblioteca.model.Prestamo;
import com.ejercicio.serviciobiblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IprestamoRepository extends JpaRepository<Prestamo, Long> {

}
