package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.dto.Chollo;
import com.example.demo.dto.Cliente;
import com.example.demo.dto.Usuario;
import com.example.demo.service.CholloService;
import com.example.demo.service.ClienteService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	@Autowired
	CholloService cholloService;
	@Autowired
	private EntityManager entityManager;
	
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
	public Cliente updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
		Cliente clienteActualizar = clienteService.getCliente(id);

		Usuario usuarioActualizar = clienteActualizar.getUsuario();

		usuarioActualizar.setNombre(cliente.getUsuario().getNombre());
        usuarioActualizar.setApellidos(cliente.getUsuario().getApellidos());
        usuarioActualizar.setTelefono(cliente.getUsuario().getTelefono());
        usuarioActualizar.setDni(cliente.getUsuario().getDni());
        usuarioActualizar.setDireccion(cliente.getUsuario().getDireccion());
        usuarioActualizar.setEmail(cliente.getUsuario().getEmail());
        usuarioActualizar.setFechaNacimiento(cliente.getUsuario().getFechaNacimiento());
        usuarioActualizar.setDeleted(cliente.getUsuario().isDeleted());
		
		return clienteActualizar;
	}

	@GetMapping("/{id}")
	public Cliente showCliente(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		return clienteService.getCliente(id);
	}

	@DeleteMapping("/{id}")
	public void deleteCliente(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		clienteService.deleteCliente(id);
	}
	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<String> guardarFavorito(@RequestBody Chollo chollo,
			@PathVariable(name = "id") Integer id) {
		// Guarda la tematica
		Chollo cholloGuardar = cholloService.getChollo(chollo.getId());

		// Obtiene el chollo por su ID
		Cliente cliente = clienteService.getCliente(id);

		// Asocia la tematica con el chollo
		if (cholloGuardar != null) {
			cliente.getChollosFavoritos().add(cholloGuardar);
			cholloGuardar.getClientesFavoritos().add(cliente);
			entityManager.persist(cliente);
			entityManager.persist(cholloGuardar);
		}

		return ResponseEntity.ok("Cliente asociado con éxito");

	}
}
