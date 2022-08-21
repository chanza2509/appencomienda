package com.example.appencomienda.modelo;

import java.util.Date;
import java.util.List;


public class EnvioDatos {
	
		private String dni;

	private Date fecha;
	private String fechax;

	public String getFechax() {
		return fechax;
	}

	public void setFechax(String fechax) {
		this.fechax = fechax;
	}

	private List<Encomienda> listaEncomiendas;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Encomienda> getListaEncomiendas() {
		return listaEncomiendas;
	}

	public void setListaEncomiendas(List<Encomienda> listaEncomiendas) {
		this.listaEncomiendas = listaEncomiendas;
	}
}
