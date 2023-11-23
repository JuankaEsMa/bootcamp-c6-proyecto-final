package org.hamaca.main.controller;

import java.util.List;

import org.hamaca.main.dto.Chollo;
import org.hamaca.main.dto.Cliente;
import org.hamaca.main.dto.Tematica;
import org.hamaca.main.dto.Usuario;
import org.hamaca.main.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listCliente(){
		return clienteService.listCliente();
	}
	 
	@PostMapping("")
	public Cliente addCliente(@RequestBody Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteService.saveCliente(cliente);
	}
	
	@PutMapping("/{id}")
	public Cliente updateCliente(@PathVariable Usuario id, @RequestBody Cliente cliente) {
		Cliente clienteActualizar = clienteService.getCliente(id);

		Usuario usuarioActualizar = clienteActualizar.getUsuario();

		usuarioActualizar.setNombre(cliente.getUsuario().getNombre());
        usuarioActualizar.setApellidos(cliente.getUsuario().getApellidos());
        usuarioActualizar.setTelefono(cliente.getUsuario().getTelefono());
        usuarioActualizar.setDni(cliente.getUsuario().getDni());
        usuarioActualizar.setDireccion(cliente.getUsuario().getDireccion());
        usuarioActualizar.setEmail(cliente.getUsuario().getEmail());
        usuarioActualizar.setFecha_nacimiento(cliente.getUsuario().getFecha_nacimientoto());
        usuarioActualizar.setDeleted(cliente.getUsuario().getDeleted());
		
		return clienteActualizar;
	}

	@GetMapping("/{id}")
	public Cliente showCliente(@PathVariable Usuario id) {
		// TODO Auto-generated method stub
		return clienteService.getCliente(id);
	}

	@DeleteMapping("/{id}")
	public void deleteCliente(@PathVariable Usuario id) {
		// TODO Auto-generated method stub
		clienteService.deleteCliente(id);
	}
}
