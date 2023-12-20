package com.example.demo.controller;

import lombok.RequiredArgsConstructor;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.Empleado;
import com.example.demo.dto.Cliente;
import com.example.demo.dto.Usuario;
import com.example.demo.service.ClienteService;
import com.example.demo.service.EmpleadoService;
import com.example.demo.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

import com.example.demo.dto.UsuarioRecord;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EmpleadoService empleadoService;
	
    @GetMapping("/all")
    public ResponseEntity<List<UsuarioRecord>> getAllUsers(HttpServletRequest request){
		Empleado empleado = cogerEmpleadoConToken();
        if(empleado != null) {
            return new ResponseEntity<>(usuarioService.getAllUsers(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }
    @PostMapping("")
    public ResponseEntity<Usuario> add(@RequestBody Usuario usuario){
    	Cliente cliente =  new Cliente();
    	usuario.setRoles("USER");
    	usuarioService.add(usuario);
    	cliente.setUsuario(usuario);
    	clienteService.saveCliente(cliente);
        return ResponseEntity.ok(usuario);
    }
    @PostMapping("/addEmpleado")
    public ResponseEntity<Usuario> addEmpleado(@RequestBody Usuario usuario){
    	Empleado empleado =  new Empleado();
    	usuarioService.add(usuario);
    	empleado.setUsuario(usuario);
    	empleadoService.saveEmpleado(empleado);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("")
    public ResponseEntity<Usuario> getByEmail(HttpServletRequest request, @RequestParam(name="email",required=false) String email){
    	
        Cliente cliente = cogerClienteConToken();
        Empleado empleado = cogerEmpleadoConToken();
        if(empleado != null && email != null) {
        	return ResponseEntity.ok(usuarioService.getUser(email));
        }else if(cliente != null){
            return ResponseEntity.ok(cliente.getUsuario());
        }else if(empleado != null) {
            return ResponseEntity.ok(empleado.getUsuario());
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> delete(@PathVariable("email") String email, HttpServletRequest request){
    	//Implementar borrado lógico en vez de literal
		Empleado empleado = cogerEmpleadoConToken();
		Cliente cliente = cogerClienteConToken();
		Usuario usuarioBorrar = usuarioService.getUser(email);
		if(cliente.getUsuario() == usuarioBorrar) {
	    	usuarioService.delete(email);
	    	return ResponseEntity.ok("Tu usuario ha sido borrado");
		}else if(empleado != null) {
			usuarioService.delete(email);
			return ResponseEntity.ok("El usuario ha sido borrado");
		}else {
			return new ResponseEntity<>("No estás autorizado para borrar éste cliente", HttpStatus.FORBIDDEN);
		}
    }

	@PutMapping("/{email}")
	public ResponseEntity<Usuario> updateUsuario(HttpServletRequest request, @RequestBody Usuario usuario, @PathVariable("email") String email) {
		Empleado empleado = cogerEmpleadoConToken();
		Cliente cliente = cogerClienteConToken();
        Usuario usuarioActualizar;
		if(empleado != null) {
			usuarioActualizar = usuarioService.getUser(email);
		}else{
			usuarioActualizar = cliente.getUsuario();
		}
		usuarioActualizar.setNombre(usuario.getNombre());
		usuarioActualizar.setApellidos(usuario.getApellidos());
		usuarioActualizar.setTelefono(usuario.getTelefono());
		usuarioActualizar.setDni(usuario.getDni());
		usuarioActualizar.setDireccion(usuario.getDireccion());
		usuarioActualizar.setEmail(usuario.getEmail());
		usuarioActualizar.setFechaNacimiento(usuario.getFechaNacimiento());
		usuarioActualizar.setDeleted(usuario.isDeleted());
		usuarioActualizar.setApellidos(usuario.getApellidos());
		
		usuarioService.update(usuarioActualizar);
		return new ResponseEntity<>(usuarioActualizar, HttpStatus.OK);
	}

	public Cliente cogerClienteConToken() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails =(UserDetails)auth.getPrincipal();
		Usuario usuario = usuarioService.getUser(userDetails.getUsername());
		Cliente cliente = clienteService.findClienteByUsuario(usuario);
		return cliente;
	}
	public Empleado cogerEmpleadoConToken() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails =(UserDetails)auth.getPrincipal();
		Usuario usuario = usuarioService.getUser(userDetails.getUsername());
		Empleado empleado = empleadoService.findEmpleadoByUsuario(usuario);
		return empleado;
	}
}