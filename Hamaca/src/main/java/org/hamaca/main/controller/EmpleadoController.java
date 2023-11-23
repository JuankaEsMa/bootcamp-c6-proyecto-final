package org.hamaca.main.controller;

import java.util.List;

import org.hamaca.main.dto.Empleado;
import org.hamaca.main.dto.Usuario;
import org.hamaca.main.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		usuarioActualizar.setFecha_nacimiento(empleado.getUsuario().getFecha_nacimientoto());
		usuarioActualizar.setDeleted(empleado.getUsuario().getDeleted());
		usuarioActualizar.setApellidos(empleado.getUsuario().getApellidos());

		empleadoActualizar.setTarjeta(empleado.getTarjeta());

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
