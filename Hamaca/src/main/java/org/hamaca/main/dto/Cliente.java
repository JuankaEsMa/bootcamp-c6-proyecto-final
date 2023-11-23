package org.hamaca.main.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;

public class Cliente{

	@Id
	@JoinColumn(name="Id")
	private Usuario id;
	@Column(name="Cuenta_bancaria")
	private String cuenta_bancaria;

	@ManyToMany(mappedBy = "Id_Cliente")
    @JsonIgnoreProperties("Id_Cliente")
	private List<Chollo> favoritos;
	
	public String getCuenta_bancaria() {
		return cuenta_bancaria;
	}

	public void setCuenta_bancaria(String cuenta_bancaria) {
		this.cuenta_bancaria = cuenta_bancaria;
	}
}
