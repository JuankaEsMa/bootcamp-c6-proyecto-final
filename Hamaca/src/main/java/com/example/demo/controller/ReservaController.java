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

import com.example.demo.dto.Reserva;
import com.example.demo.service.ReservaService;

@RestController
@RequestMapping("reserva")
public class ReservaController {
	@Autowired
	ReservaService reservaService;
	
	@GetMapping("")
	public List<Reserva> listReserva() {
		// TODO Auto-generated method stub
		return reservaService.listReserva();
	}
	@PostMapping("")
	public Reserva addReserva(@RequestBody Reserva tematica) {
		// TODO Auto-generated method stub
		return reservaService.saveReserva(tematica);
	}
	@PutMapping("/{id}")
	public Reserva updateReserva(@PathVariable Integer id, @RequestBody Reserva tematica) {
		Reserva reservaActualizar = reservaService.getReserva(id);
		
		reservaActualizar.setFechaCompra(tematica.getFechaCompra());
		reservaActualizar.setNumNoches(tematica.getNumNoches());
		reservaActualizar.setNumPersonas(tematica.getNumPersonas());
		reservaActualizar.setNota(tematica.getNota());
		reservaActualizar.setId_Chollo(tematica.getId_Chollo());
		reservaActualizar.setId_Cliente(tematica.getId_Cliente());

		return reservaActualizar;
	}
	@GetMapping("/{id}")
	public Reserva showReserva(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		return reservaService.getReserva(id);
	}
	@DeleteMapping("/{id}")
	public void deleteReserva(@PathVariable Integer id) {
		// TODO Auto-generated method stub
		reservaService.deleteReserva(id);
	}
}
