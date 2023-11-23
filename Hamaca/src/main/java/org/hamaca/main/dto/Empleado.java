package org.hamaca.main.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

public class Empleado{
	@Id
	@JoinColumn(name="Id")
	private Usuario id;
	@Column(name="Tarjeta")
	private String tarjeta;
	
	@ManyToMany(mappedBy = "Id_Tematica")
    @JsonIgnoreProperties("Id_Tematica")
	private List<Chollo> chollos;

	
	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
}
