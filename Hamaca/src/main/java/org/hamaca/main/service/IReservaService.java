package org.hamaca.main.service;

import java.util.List;

import org.hamaca.main.dto.Reserva;

public interface IReservaService {
	public List<Reserva> listReserva();
	public Reserva saveReserva(Reserva reserva);
	public Reserva getReserva(Integer id);
	public void deleteReserva(Integer id);
}
