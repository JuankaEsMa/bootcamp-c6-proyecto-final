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

import com.example.demo.dto.Localidad;
import com.example.demo.service.LocalidadService;

@RestController
@RequestMapping("localidad")
public class LocalidadController {

	@Autowired
	LocalidadService localidadService;

	@GetMapping("")
	public List<Localidad> listLocalidad() {
		// TODO Auto-generated method stub
		return localidadService.listLocalidad();
	}

	@PostMapping("")
	public Localidad addLocalidad(@RequestBody Localidad localidad) {
		// TODO Auto-generated method stub
		return localidadService.saveLocalidad(localidad);
	}

	@PutMapping("/{id}")
	public Localidad updateLocalidad(@PathVariable Integer id, @RequestBody Localidad localidad) {
		Localidad localidadActualizar = localidadService.getLocalidad(id);

		localidadActualizar.setNombre(localidad.getNombre());
		localidadActualizar.setPais(localidad.getPais());

		return localidadActualizar;
	}

	@GetMapping("/{id}")
	public Localidad showLocalidad(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		return localidadService.getLocalidad(id);
	}

	@DeleteMapping("/{id}")
	public void deleteLocalidad(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		localidadService.deleteLocalidad(id);
	}
}
