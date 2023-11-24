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

import com.example.demo.dto.Tematica;
import com.example.demo.service.TematicaService;

@RestController
@RequestMapping("tematica")
public class TematicaController {
	
	@Autowired
	TematicaService tematicaService;
	
	@GetMapping("")
	public List<Tematica> listTematica() {
		// TODO Auto-generated method stub
		return tematicaService.listTematica();
	}
	@PostMapping("")
	public Tematica addTematica(@RequestBody Tematica tematica) {
		// TODO Auto-generated method stub
		return tematicaService.saveTematica(tematica);
	}
	@PutMapping("/{id}")
	public Tematica updateTematica(@PathVariable Integer id, @RequestBody Tematica tematica) {
		Tematica tematicaActualizar = tematicaService.getTematica(id);
		
		tematicaActualizar.setNombre(tematica.getNombre());

		return tematicaActualizar;
	}
	@GetMapping("/{id}")
	public Tematica showTematica(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		return tematicaService.getTematica(id);
	}
	@DeleteMapping("/{id}")
	public void deleteTematica(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		tematicaService.deleteTematica(id);
	}
}
