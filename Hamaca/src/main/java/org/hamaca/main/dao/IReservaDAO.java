package org.hamaca.main.dao;

import org.hamaca.main.dto.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservaDAO extends JpaRepository<Reserva, Integer>{

}
