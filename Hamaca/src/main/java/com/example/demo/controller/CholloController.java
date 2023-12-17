package com.example.demo.controller;

import java.sql.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
import com.example.demo.dto.Tematica;
import com.example.demo.service.CholloService;
import com.example.demo.service.LocalidadService;
import com.example.demo.service.PaisService;
import com.example.demo.service.TematicaService;
import org.springframework.data.domain.Pageable; 

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
	public ResponseEntity<Map<String, Object>> listChollo(@RequestParam(name = "localidad", required = false) String localidadName,
			@RequestParam(name = "tematica", required = false) String tematicaName,
			@RequestParam(name = "pais", required = false) String paisName,
			@RequestParam(name = "dataInicio", required = false) String dataInicioString,
			@RequestParam(name = "dataFinal", required = false) String dataFinalString,
			@RequestParam(name = "precioMin", required = false) Double precioMin,
			@RequestParam(name = "precioMax", required = false) Double precioMax,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Pageable pageable = PageRequest.of(page, size);
		
		
		// TODO Auto-generated method stub
		ArrayList<Chollo> filtro = new ArrayList<Chollo>();
		ArrayList<Chollo> allChollos = new ArrayList<>(cholloService.listChollo());
		boolean isFiltered = false;
		
		if (localidadName != null && !localidadName.isBlank()) {
			ArrayList<Chollo> chollosByLocalidades = new ArrayList<>();
			List<Localidad> localidades = localidadService.findLocalidadByNombre(localidadName);
			for (int i = 0; i < localidades.size(); i++) {
				chollosByLocalidades.addAll(cholloService.findCholloByLocalidad(localidades.get(i)));	
			}
			if (!isFiltered) {
				filtro = new ArrayList<Chollo>(chollosByLocalidades);
				isFiltered = true;
			} else {
				filtro.retainAll(chollosByLocalidades);
			}
		}
		if (tematicaName != null && !tematicaName.isBlank()) {
			ArrayList<Chollo> chollosByTematica = new ArrayList<>();
			List<Tematica> tematicas = tematicaService.findTematicaByName(tematicaName);
			for (int i = 0; i < tematicas.size(); i++) {
				chollosByTematica.addAll(cholloService.findCholloByTematica(tematicas.get(i)));	
			}
			if (!isFiltered) {
				filtro = new ArrayList<Chollo>(chollosByTematica);
				isFiltered = true;
			} else {
				filtro.retainAll(chollosByTematica);
			}
		}
		if (paisName != null && !paisName.isBlank()) {
			ArrayList<Chollo> cholloByPais = new ArrayList<Chollo>();
			List<Localidad> localidades = localidadService.findLocalidadByPais(paisService.findPaisByNombre(paisName));
			for (int i = 0; i < localidades.size(); i++) {
				cholloByPais.addAll(cholloService.findCholloByLocalidad(localidades.get(i)));
			}
			if (!isFiltered) {
				filtro = new ArrayList<Chollo>(cholloByPais);
				isFiltered = true;
			} else {
				filtro.retainAll(cholloByPais);
			}
		}
		if (dataInicioString != null && dataFinalString != null && !dataInicioString.isBlank() && !dataFinalString.isBlank()) {
			try {
				Date dataInicio = Date.valueOf(dataInicioString);
				Date dataFinal = Date.valueOf(dataFinalString);
				if (!isFiltered) {
					filtro = new ArrayList<Chollo>(cholloService.findCholloByDates(dataInicio, dataFinal ));
					isFiltered = true;
				} else {
					filtro.retainAll(cholloService.findCholloByDates(dataInicio, dataFinal));
				}
			}catch(Exception e) {
				System.out.println("Mensaje error fechas: " + e.toString());
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}

		if (precioMin != null && precioMax != null) {
			if (!isFiltered) {
				filtro = new ArrayList<Chollo>(cholloService.findCholloByPrecios(precioMin, precioMax ));
				isFiltered = true;
			} else {
				filtro.retainAll(cholloService.findCholloByPrecios(precioMin, precioMax));
			}
		}

		if (isFiltered) {
			allChollos.retainAll(filtro);
		}
		int start = size*page;
		int end = Math.min((start + size), allChollos.size());
		try {
		    Page<Chollo> cholloPage = new PageImpl<Chollo>(allChollos.subList(start, end), pageable, allChollos.size());
		    Map<String, Object> response = new HashMap<>();
			response.put("currentPage", cholloPage.getNumber());
			response.put("totalItems", cholloPage.getTotalElements());
			response.put("totalPages", cholloPage.getTotalPages());
			response.put("Chollos", cholloPage.getContent());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getCause());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

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
