package org.hamaca.main.controller;

import java.util.List;
import org.hamaca.main.dto.Chollo;
import org.hamaca.main.dto.Tematica;
import org.hamaca.main.service.CholloService;
import org.hamaca.main.service.TematicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import jakarta.persistence.EntityManager;

public class CholloController {
	
	@Autowired
	CholloService cholloService;
	@Autowired
	TematicaService tematicaService;
	@Autowired
    private EntityManager entityManager;
	
	public List<Chollo> listChollo() {
		// TODO Auto-generated method stub
		return cholloService.listChollo();
	}

	public Chollo addChollo(Chollo chollo) {
		// TODO Auto-generated method stub
		return cholloService.saveChollo(chollo);
	}

	public Chollo updateChollo(Integer id, Chollo chollo) {
		Chollo cholloActualizar = cholloService.getChollo(id);
		
		cholloActualizar.setTitulo(chollo.getTitulo());
		cholloActualizar.setImagen(chollo.getImagen());
		cholloActualizar.setPrecioPersona(chollo.getPrecioPersona());
		cholloActualizar.setCantidadPersonas(chollo.getCantidadPersonas());
		cholloActualizar.setDescripcion(chollo.getDescripcion());
		cholloActualizar.setDeleted(chollo.isDeleted());
		cholloActualizar.setFechaCaducidad(chollo.getFechaCaducidad());
		cholloActualizar.setLocalidad(chollo.getLocalidad());
		cholloActualizar.setEmpleado(chollo.getEmpleado());

		return cholloActualizar;
	}
	
	public Chollo showChollo(Integer id) {
		// TODO Auto-generated method stub
		return cholloService.getChollo(id);
	}

	public void deleteChollo(Integer id) {
		// TODO Auto-generated method stub
		cholloService.deleteChollo(id);
	}
	
	public ResponseEntity<String> salvarEstudianteCurso(@RequestBody Tematica tematica, @PathVariable(name="id")Integer id) {
	    // Guarda el estudiante
		Tematica tematicaGuardar = tematicaService.getTematica(tematica.getId());

	    // Obtiene el curso por su ID
	    Chollo chollo = cholloService.getChollo(id);

	    // Asocia el estudiante con el curso
	    if (tematicaGuardar != null) {
	    	chollo.getTematicas().add(tematicaGuardar);
	    	tematicaGuardar.getChollos().add(chollo);
	        entityManager.persist(chollo);
	        entityManager.persist(tematicaGuardar);
	    }

	    return ResponseEntity.ok("Tematica asociada con éxito");
	    
	}
	
}
