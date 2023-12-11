package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.dto.Cliente;
import com.example.demo.dto.Empleado;
import com.example.demo.dto.Reserva;
import com.example.demo.dto.Usuario;
import com.example.demo.jwt.JWTService;
import com.example.demo.service.CholloService;
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
	@Autowired
	private CholloService cholloService;
	
	@GetMapping("")
	public ResponseEntity<List<Reserva>> listReserva(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Cliente cliente = cogerClienteConToken(request.getHeader("Authorization"));
		if(cliente != null)	{
            return ResponseEntity.ok(reservaService.findReservaByCliente(cliente));
		}else {
			//Implementar Empleado puede acceder a todos
			return ResponseEntity.ok(reservaService.listReserva());
		}
	}
	@PostMapping("")
	public ResponseEntity<Reserva> addReserva(HttpServletRequest request,
			@RequestParam(name="idChollo", required=true) Integer idChollo, 
			@RequestParam(name="numNoches", required=true) Integer numNoches, 
			@RequestParam(name="numPersonas", required=true) Integer numPersonas) {
		// TODO Auto-generated method stub
		Cliente cliente = cogerClienteConToken(request.getHeader("Authorization"));
		Reserva reserva = new Reserva();
		Chollo chollo = cholloService.getChollo(idChollo); 
//		Calendar cal = Calendar.getInstance();

		if(cliente != null && chollo != null) {
			reserva.setCliente(cliente);
			reserva.setChollo(chollo);
//			reserva.setFechaCompra(cal.getTime());
			reserva.setNumNoches(numNoches);
			reserva.setNumPersonas(numPersonas);
			return ResponseEntity.ok(reservaService.saveReserva(reserva));
		}else {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<Reserva> updateReserva(HttpServletRequest request, @PathVariable Integer id, @RequestBody Reserva reserva) {
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
				return ResponseEntity.ok(reservaService.saveReserva(reservaActualizar));
			}
		}else if(empleado != null){
			reservaActualizar.setFechaCompra(reserva.getFechaCompra());
			reservaActualizar.setNumNoches(reserva.getNumNoches());
			reservaActualizar.setNumPersonas(reserva.getNumPersonas());
			reservaActualizar.setNota(reserva.getNota());
			reservaActualizar.setChollo(reserva.getChollo());
			reservaActualizar.setCliente(reserva.getCliente());
			return ResponseEntity.ok(reservaService.saveReserva(reservaActualizar));
		}
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Reserva> showReserva(HttpServletRequest request, @PathVariable Integer id) {
		// TODO Auto-generated method stub
		Cliente cliente = cogerClienteConToken(request.getHeader("Authorization"));
		Empleado empleado = cogerEmpleadoConToken(request.getHeader("Authorization"));
		if(cliente != null) {
			List<Reserva> reservasCliente = reservaService.findReservaByCliente(cliente);
			Reserva reservaMostrar = reservaService.getReserva(id);
			if(reservasCliente.contains(reservaMostrar)) {
				return ResponseEntity.ok(reservaService.getReserva(id));
			}
		}else if(empleado != null) {
			return ResponseEntity.ok(reservaService.getReserva(id));
		}
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);		
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
