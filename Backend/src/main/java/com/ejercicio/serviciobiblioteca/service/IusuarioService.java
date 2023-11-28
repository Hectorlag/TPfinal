package com.ejercicio.serviciobiblioteca.service;

import com.ejercicio.serviciobiblioteca.model.Usuario;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IusuarioService {

    //Traer todos
    public List<Usuario> getUsuarios();

    //Crear
    public void saveUsuario(Usuario usuario);

    //Editar
    public Usuario editarUsuario(@PathVariable Long idUsuario, Usuario usuario);

    //Borrar
    public void deleteUsuario(@PathVariable Long idUsuario);

    //Buscar
    public Usuario findUsuario(@PathVariable Long idUsuario);

    //Login
    public boolean autenticarUsuario(Usuario usuario);

}
