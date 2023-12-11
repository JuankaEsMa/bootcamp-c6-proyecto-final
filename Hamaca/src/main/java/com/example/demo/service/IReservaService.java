package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Cliente;
import com.example.demo.dto.Reserva;

public interface IReservaService {
	public List<Reserva> listReserva();
	public Reserva saveReserva(Reserva reserva);
	public Reserva getReserva(Integer id);
	public void deleteReserva(Integer id);
	public List<Reserva> findReservaByCliente(Cliente cliente);
}
