package org.hamaca.main.service;

import java.util.List;

import org.hamaca.main.dao.IReservaDAO;
import org.hamaca.main.dto.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService implements IReservaService{

	@Autowired
	IReservaDAO reservaDAO;
	
	@Override
	public List<Reserva> listReserva() {
		// TODO Auto-generated method stub
		return reservaDAO.findAll();
	}

	@Override
	public Reserva saveReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		return reservaDAO.save(reserva);
	}

	@Override
	public Reserva getReserva(Integer id) {
		// TODO Auto-generated method stub
		return reservaDAO.findById(id).get();
	}

	@Override
	public void deleteReserva(Integer id) {
		// TODO Auto-generated method stub
		reservaDAO.deleteById(id);
	}

}
