package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IReservaDAO;
import com.example.demo.dto.Reserva;

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
