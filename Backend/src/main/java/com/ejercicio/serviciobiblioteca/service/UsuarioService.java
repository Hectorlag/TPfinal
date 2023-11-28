package com.ejercicio.serviciobiblioteca.service;

import com.ejercicio.serviciobiblioteca.model.Usuario;
import com.ejercicio.serviciobiblioteca.repository.IusuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IusuarioService{

    @Autowired
    private IusuarioRepository usuarioRepo;
    @Override
    public List<Usuario> getUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public void saveUsuario(Usuario usuario) {

        usuarioRepo.save(usuario);
    }

    @Override
    public Usuario editarUsuario(Long idUsuario, Usuario usuario) {
        Usuario u = this.findUsuario(idUsuario);
        u.setNombre(usuario.getNombre());
        u.setContrasenia(usuario.getContrasenia());

        return usuarioRepo.save(u);
    }

    @Override
    public void deleteUsuario(Long idUsuario) {

        usuarioRepo.deleteById(idUsuario);
    }

    @Override
    public Usuario findUsuario(Long idUsuario) {
        return usuarioRepo.findById(idUsuario).orElse(null);
    }

    @Override
    public boolean autenticarUsuario(Usuario usuario) {

        // Lógica para autenticar al usuario
        Optional<Usuario> usuarioEncontrado = usuarioRepo.findByNombre(usuario.getNombre());

        return usuarioEncontrado.isPresent() && usuarioEncontrado.get().getContrasenia().equals(usuario.getContrasenia());
    }
}
