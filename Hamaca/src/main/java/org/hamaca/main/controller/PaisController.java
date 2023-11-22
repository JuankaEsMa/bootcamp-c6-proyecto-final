package org.hamaca.main.controller;

import java.util.List;

import org.hamaca.main.dto.Pais;
import org.hamaca.main.service.PaisService;
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
@RequestMapping("pais")
public class PaisController {

	@Autowired
	PaisService paisService;

	@GetMapping("")
	public List<Pais> listPais() {
		// TODO Auto-generated method stub
		return paisService.listPais();
	}

	@PostMapping("")
	public Pais addPais(@RequestBody Pais pais) {
		// TODO Auto-generated method stub
		return paisService.savePais(pais);
	}

	@PutMapping("/{id}")
	public Pais updatePais(@PathVariable Integer id, @RequestBody Pais pais) {
		Pais paisActualizar = paisService.getPais(id);
		paisActualizar.setNombre(pais.getNombre());

		return paisActualizar;
	}

	@GetMapping("/{id}")
	public Pais showPais(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		return paisService.getPais(id);
	}

	@DeleteMapping("/{id}")
	public void deletePais(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		paisService.deletePais(id);
	}

}
