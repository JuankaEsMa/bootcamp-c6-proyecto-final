package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Empleado;
import com.example.demo.dto.Usuario;
import com.example.demo.service.EmpleadoService;

@RestController
@RequestMapping("empleado")
public class EmpleadoController {
	@Autowired
	EmpleadoService empleadoService;

	@GetMapping("")
	public List<Empleado> listEmpleado() {
		// TODO Auto-generated method stub
		return empleadoService.listEmpleado();
	}

	@PostMapping("")
	public Empleado addEmpleado(@RequestBody Empleado empleado) {
		// TODO Auto-generated method stub
		return empleadoService.saveEmpleado(empleado);
	}

	@PutMapping("/{id}")
	public Empleado updateEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
		Empleado empleadoActualizar = empleadoService.getEmpleado(id);
		Usuario usuarioActualizar = empleadoActualizar.getUsuario();

		usuarioActualizar.setNombre(empleado.getUsuario().getNombre());
		usuarioActualizar.setApellidos(empleado.getUsuario().getApellidos());
		usuarioActualizar.setTelefono(empleado.getUsuario().getTelefono());
		usuarioActualizar.setDni(empleado.getUsuario().getDni());
		usuarioActualizar.setDireccion(empleado.getUsuario().getDireccion());
		usuarioActualizar.setEmail(empleado.getUsuario().getEmail());
		usuarioActualizar.setFechaNacimiento(empleado.getUsuario().getFechaNacimiento());
		usuarioActualizar.setDeleted(empleado.getUsuario().isDeleted());
		usuarioActualizar.setApellidos(empleado.getUsuario().getApellidos());

		empleadoActualizar.setCuentaBancaria(empleado.getCuentaBancaria());;

		return empleadoActualizar;
	}

	@GetMapping("/{id}")
	public Empleado showEmpleado(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		return empleadoService.getEmpleado(id);
	}

	@DeleteMapping("/{id}")
	public void deleteEmpleado(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		empleadoService.deleteEmpleado(id);
	}
}
