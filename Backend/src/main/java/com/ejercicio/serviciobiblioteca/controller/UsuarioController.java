package com.ejercicio.serviciobiblioteca.controller;

import com.ejercicio.serviciobiblioteca.model.Usuario;
import com.ejercicio.serviciobiblioteca.service.IusuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IusuarioService usuarioServi;

    //Traer todos
    @GetMapping("/traer")
    public List<Usuario> getUsuarios(){

        return usuarioServi.getUsuarios();
    }

    //Crear
    @PostMapping("/crear")
    public ResponseEntity<?> saveUsuario(@Valid @RequestBody Usuario usuario, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errores = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);

        }

        usuarioServi.saveUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado correctamente");
    }

    //Editar
    @PutMapping("/editar/{idUsuario}")
    public ResponseEntity<?> editUsuario(@Valid @PathVariable Long idUsuario, @RequestBody Usuario usuario, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errores = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);
        }


        usuarioServi.editarUsuario(idUsuario, usuario);
        Usuario u = usuarioServi.findUsuario(idUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }

    //Borrar
    @DeleteMapping("/borrar/{idUsuario}")
    public String deleteUsuario(@PathVariable Long idUsuario){

        usuarioServi.deleteUsuario(idUsuario);

        return "Usuario eliminado correctamente";
    }

    @GetMapping("/traer/{idUsuario}")
    public Usuario getUsuarioXid(@PathVariable Long idUsuario){

        return usuarioServi.findUsuario(idUsuario);
    }

    //Logueo
    // Endpoint para la autenticación (login) de un usuario
    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody Usuario usuario) {
        if (usuarioServi.autenticarUsuario(usuario)) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
}

