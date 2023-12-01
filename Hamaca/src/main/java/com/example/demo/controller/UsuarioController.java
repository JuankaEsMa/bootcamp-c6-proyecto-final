package com.example.demo.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.Usuario;
import com.example.demo.service.UsuarioService;
import com.example.demo.dto.UsuarioRecord;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<List<UsuarioRecord>> getAllUsers(){
        return new ResponseEntity<>(usuarioService.getAllUsers(), HttpStatus.FOUND);
    }
    @PostMapping("")
    public ResponseEntity<Usuario> add(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.add(usuario));
    }

    @GetMapping("/{email}")
    public Usuario getByEmail(@PathVariable("email") String email){
        return  usuarioService.getUser(email);
    }

    @DeleteMapping("/{email}")
    public void delete(@PathVariable("email") String email){
    	usuarioService.delete(email);
    }

	@PutMapping("/{id}")
	public Usuario updateUsuario(@PathVariable String email, @RequestBody Usuario usuario) {
		Usuario usuarioActualizar = usuarioService.getUser(email);

		usuarioActualizar.setNombre(usuario.getNombre());
		usuarioActualizar.setApellidos(usuario.getApellidos());
		usuarioActualizar.setTelefono(usuario.getTelefono());
		usuarioActualizar.setDni(usuario.getDni());
		usuarioActualizar.setDireccion(usuario.getDireccion());
		usuarioActualizar.setEmail(usuario.getEmail());
		usuarioActualizar.setFechaNacimiento(usuario.getFechaNacimiento());
		usuarioActualizar.setDeleted(usuario.isDeleted());
		usuarioActualizar.setApellidos(usuario.getApellidos());

		return usuarioActualizar;
	}

}