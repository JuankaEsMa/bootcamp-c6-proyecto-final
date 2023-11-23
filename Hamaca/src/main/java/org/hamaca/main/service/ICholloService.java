package org.hamaca.main.service;

import java.util.List;

import org.hamaca.main.dto.Chollo;

public interface ICholloService {
	public List<Chollo> listChollo();
	public Chollo saveChollo(Chollo chollo);
	public Chollo getChollo(Integer id);
	public void deleteChollo(Integer id);
}
