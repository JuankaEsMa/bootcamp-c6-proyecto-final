package org.hamaca.main.controller;

import java.util.List;
import org.hamaca.main.dto.Chollo;
import org.hamaca.main.dto.Tematica;
import org.hamaca.main.service.CholloService;
import org.hamaca.main.service.TematicaService;
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

import jakarta.persistence.EntityManager;

@RestController
@RequestMapping("chollo")
public class CholloController {

	@Autowired
	CholloService cholloService;
	@Autowired
	TematicaService tematicaService;
	@Autowired
	private EntityManager entityManager;

	@GetMapping("")
	public List<Chollo> listChollo() {
		// TODO Auto-generated method stub
		return cholloService.listChollo();
	}

	@PostMapping("")
	public Chollo addChollo(@RequestBody Chollo chollo) {
		// TODO Auto-generated method stub
		return cholloService.saveChollo(chollo);
	}

	@PutMapping("/{id}")
	public Chollo updateChollo(@PathVariable Integer id, @RequestBody Chollo chollo) {
		Chollo cholloActualizar = cholloService.getChollo(id);

		cholloActualizar.setTitulo(chollo.getTitulo());
		cholloActualizar.setImagen(chollo.getImagen());
		cholloActualizar.setPrecioPersona(chollo.getPrecioPersona());
		cholloActualizar.setCantidadPersonas(chollo.getCantidadPersonas());
		cholloActualizar.setDescripcion(chollo.getDescripcion());
		cholloActualizar.setDeleted(chollo.isDeleted());
		cholloActualizar.setFechaCaducidad(chollo.getFechaCaducidad());
		cholloActualizar.setLocalidad(chollo.getLocalidad());
		cholloActualizar.setIdEmpleado(chollo.getIdEmpleado());

		return cholloActualizar;
	}

	@GetMapping("/{id}")
	public Chollo showChollo(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		return cholloService.getChollo(id);
	}

	@DeleteMapping("/{id}")
	public void deleteChollo(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		cholloService.deleteChollo(id);
	}

	@PostMapping("/{id}")
	public ResponseEntity<String> salvarEstudianteCurso(@RequestBody Tematica tematica,
			@PathVariable(name = "id") Integer id) {
		// Guarda la tematica
		Tematica tematicaGuardar = tematicaService.getTematica(tematica.getId());

		// Obtiene el chollo por su ID
		Chollo chollo = cholloService.getChollo(id);

		// Asocia la tematica con el chollo
		if (tematicaGuardar != null) {
			chollo.getTematicas().add(tematicaGuardar);
			tematicaGuardar.getChollos().add(chollo);
			entityManager.persist(chollo);
			entityManager.persist(tematicaGuardar);
		}

		return ResponseEntity.ok("Tematica asociada con éxito");

	}

}
