package com.example.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Chollo;
import com.example.demo.dto.Localidad;
import com.example.demo.dto.Pais;
import com.example.demo.dto.Tematica;
import com.example.demo.service.CholloService;
import com.example.demo.service.LocalidadService;
import com.example.demo.service.PaisService;
import com.example.demo.service.TematicaService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("chollo")
public class CholloController {

	@Autowired
	CholloService cholloService;
	@Autowired
	TematicaService tematicaService;
	@Autowired
	LocalidadService localidadService;
	@Autowired
	PaisService paisService;
	@Autowired
	private EntityManager entityManager;

	@GetMapping("")
	public List<Chollo> listChollo(@RequestParam(name = "localidad", required = false) Integer id_localidad,
			@RequestParam(name = "tematica", required = false) Integer id_tematica,
			@RequestParam(name = "pais", required = false) Integer id_pais,
			@RequestParam(name = "dataInicio", required = false) Date dataInicio,
			@RequestParam(name = "dataFinal", required = false) Date dataFinal,
			@RequestParam(name = "precioMin", required = false) Double precioMin,
			@RequestParam(name = "precioMax", required = false) Double precioMax) {

		// TODO Auto-generated method stub
		ArrayList<Chollo> filtro = new ArrayList<Chollo>();
		ArrayList<Chollo> allChollos = new ArrayList<Chollo>(cholloService.listChollo());
		boolean isFiltered = false;

		if (id_localidad != null) {
			Localidad localidad = localidadService.getLocalidad(id_localidad);
			if (!isFiltered) {
				filtro = new ArrayList<Chollo>(cholloService.findCholloByLocalidad(localidad));
				isFiltered = true;
			} else {
				filtro.retainAll(cholloService.findCholloByLocalidad(localidad));
			}
		}
		if (id_tematica != null) {
			Tematica tematica = tematicaService.getTematica(id_tematica);
			if (!isFiltered) {
				filtro = new ArrayList<Chollo>(cholloService.findCholloByTematica(tematica));
				isFiltered = true;
			} else {
				filtro.retainAll(cholloService.findCholloByTematica(tematica));
			}
		}
		if (id_pais != null) {
			ArrayList<Chollo> filtroLocalidades = new ArrayList<Chollo>();
			List<Localidad> localidades = localidadService.findLocalidadByPais(paisService.getPais(id_pais));
			for (int i = 0; i < localidades.size(); i++) {
				filtroLocalidades.addAll(cholloService.findCholloByLocalidad(localidades.get(i)));
			}
			if (!isFiltered) {
				filtro = new ArrayList<Chollo>(filtroLocalidades);
				isFiltered = true;
			} else {
				filtro.retainAll(filtroLocalidades);
			}
		}
		if (dataInicio != null && dataFinal != null) {
			if (!isFiltered) {
				filtro = new ArrayList<Chollo>(cholloService.findCholloByDates(dataInicio, dataFinal));
				isFiltered = true;
			} else {
				filtro.retainAll(cholloService.findCholloByDates(dataInicio, dataFinal));
			}
		}

		if (precioMin != null && precioMax != null) {
			if (!isFiltered) {
				filtro = new ArrayList<Chollo>(cholloService.findCholloByPrecios(precioMin, precioMax));
				isFiltered = true;
			} else {
				filtro.retainAll(cholloService.findCholloByPrecios(precioMin, precioMax));
			}
		}

		if (isFiltered) {
			allChollos.retainAll(filtro);
		}
		return allChollos;
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
	@Transactional
	public ResponseEntity<String> guardarTematica(@RequestBody Tematica tematica,
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
