package org.hamaca.main.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

public class Cliente{

	@Id
	@JoinColumn(name="Id")
	private Usuario usuario;
	@Column(name="Cuenta_bancaria")
	private String cuenta_bancaria;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Favorito",
        joinColumns = { @JoinColumn(name = "Id_Cliente") },
        inverseJoinColumns = { @JoinColumn(name = "Id_Chollo")}
    )
    private List<Chollo> favoritos;
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCuenta_bancaria() {
		return cuenta_bancaria;
	}

	public void setCuenta_bancaria(String cuenta_bancaria) {
		this.cuenta_bancaria = cuenta_bancaria;
	}
}
