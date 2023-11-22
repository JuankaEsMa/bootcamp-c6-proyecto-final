package org.hamaca.main.service;

import java.util.List;
import org.hamaca.main.dto.Pais;
import org.springframework.stereotype.Service;

public interface IPaisService {

	public List <Pais> listPais();

	public Pais savePais(Pais pais);

	public Pais getPais(Integer id);

	public void deletePais(Integer id);
}
