package org.hamaca.main.dao;

import org.hamaca.main.dto.Usuario;
import org.hamaca.main.dto.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteDAO extends JpaRepository<Cliente, Usuario>{

}
