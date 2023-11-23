package org.hamaca.main.dao;

import org.hamaca.main.dto.Empleado;
import org.hamaca.main.dto.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpleadoDAO  extends JpaRepository<Empleado, Usuario>{

}
