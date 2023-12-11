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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Chollo;
import com.example.demo.dto.Cliente;
import com.example.demo.dto.Empleado;
import com.example.demo.dto.Reserva;
import com.example.demo.dto.Usuario;
import com.example.demo.jwt.JWTService;
import com.example.demo.service.ClienteService;
import com.example.demo.service.EmpleadoService;
import com.example.demo.service.ReservaService;
import com.example.demo.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("reserva")
public class ReservaController {
	@Autowired
	ReservaService reservaService;
	@Autowired
    private JWTService jwtService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EmpleadoService empleadoService;
	
	@GetMapping("")
	public List<Reserva> listReserva(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Cliente cliente = cogerClienteConToken(request.getHeader("Authorization"));
		if(cliente != null)	{
            return reservaService.findReservaByCliente(cliente);
		}else {
			//Implementar Empleado puede acceder a todos
			return reservaService.listReserva();
		}
		
	}
	@PostMapping("")
	public Reserva addReserva(HttpServletRequest request,@RequestBody Chollo chollo, @RequestParam(name="numNoches", required=true) Integer numNoches, @RequestParam(name="numPersonas", required=true) Integer numPersonas) {
		// TODO Auto-generated method stub
		Cliente cliente = cogerClienteConToken(request.getHeader("Authorization"));
		Reserva reserva = new Reserva();
		if(cliente != null && chollo != null) {
			reserva.setCliente(cliente);
			reserva.setChollo(chollo);
			return reservaService.saveReserva(reserva);
		}else {
			return null;
		}
	}
	@PutMapping("/{id}")
	public Reserva updateReserva(HttpServletRequest request, @PathVariable Integer id, @RequestBody Reserva reserva) {
		Cliente cliente = cogerClienteConToken(request.getHeader("Authorization"));
		Empleado empleado = cogerEmpleadoConToken(request.getHeader("Authorization"));
		Reserva reservaActualizar = reservaService.getReserva(id);

		if(cliente != null) {
			List<Reserva> reservasCliente = reservaService.findReservaByCliente(cliente);
			if(reservasCliente.contains(reservaActualizar)) {
				reservaActualizar.setFechaCompra(reserva.getFechaCompra());
				reservaActualizar.setNumNoches(reserva.getNumNoches());
				reservaActualizar.setNumPersonas(reserva.getNumPersonas());
				reservaActualizar.setNota(reserva.getNota());
				reservaActualizar.setChollo(reserva.getChollo());
				reservaActualizar.setCliente(cliente);
				return reservaService.saveReserva(reservaActualizar);
			}else {
				//Implementar fallo por cliente intentando acceder a Reserva que no es suya
				return null;
			}

		}else if(empleado != null){
			reservaActualizar.setFechaCompra(reserva.getFechaCompra());
			reservaActualizar.setNumNoches(reserva.getNumNoches());
			reservaActualizar.setNumPersonas(reserva.getNumPersonas());
			reservaActualizar.setNota(reserva.getNota());
			reservaActualizar.setChollo(reserva.getChollo());
			reservaActualizar.setCliente(reserva.getCliente());
			return reservaService.saveReserva(reservaActualizar);
		}
		return null;
		
	}
	@GetMapping("/{id}")
	public Reserva showReserva(HttpServletRequest request, @PathVariable Integer id) {
		// TODO Auto-generated method stub
		Cliente cliente = cogerClienteConToken(request.getHeader("Authorization"));
		Empleado empleado = cogerEmpleadoConToken(request.getHeader("Authorization"));
		if(cliente != null) {
			List<Reserva> reservasCliente = reservaService.findReservaByCliente(cliente);
			Reserva reservaMostrar = reservaService.getReserva(id);
			if(reservasCliente.contains(reservaMostrar)) {
				return reservaService.getReserva(id);
			}else {
				//Implementar error Cliente intentando ver Reserva que no es suya
			}
		}else if(empleado != null) {
			return reservaService.getReserva(id);
		}
		return null;
	}
	@DeleteMapping("/{id}")
	public void deleteReserva(HttpServletRequest request, @PathVariable Integer id) {
		// TODO Auto-generated method stub
		Cliente cliente = cogerClienteConToken(request.getHeader("Authorization"));
		Empleado empleado = cogerEmpleadoConToken(request.getHeader("Authorization"));
		if(cliente != null) {
			List<Reserva> reservasCliente = reservaService.findReservaByCliente(cliente);
			Reserva reservaBorrar = reservaService.getReserva(id);
			if(reservasCliente.contains(reservaBorrar)) {
				reservaService.deleteReserva(id);
			}else {
				//Implementar Error por Cliente intentando borrar reserva que no es suya
			}
		}else if(empleado != null) {
			reservaService.deleteReserva(id);
		}
	}
	public Cliente cogerClienteConToken(String authHeader) {
		String token = null;
		String userName = null;
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			try {
				token = authHeader.substring(7);
	            userName = jwtService.extractUsernameFromToken(token);
	            Usuario usuario = usuarioService.getUser(userName);
	            Cliente cliente = clienteService.findClienteByUsuario(usuario);
	    		return cliente;
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	public Empleado cogerEmpleadoConToken(String authHeader) {
		String token = null;
		String userName = null;
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			try {
				token = authHeader.substring(7);
	            userName = jwtService.extractUsernameFromToken(token);
	            Usuario usuario = usuarioService.getUser(userName);
	            Empleado empleado = empleadoService.findEmpleadoByUsuario(usuario);
	    		return empleado;
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
}
