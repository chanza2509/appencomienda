package com.example.appencomienda.modelo;

import java.util.Date;


public class Encomienda {
	

	private int id;
    private Date fechaRegistro;
    private String dni;

    private String nombres;
    private String destino;
    private String apellidos;
    private String encomienda;
    private Date fechaEntrega;
    private double precio;
    private int idEstado;
    public Encomienda(){

    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Encomienda(int id, String encomienda, String dni, String nombres, String apellidos, int idEstado) {
        this.id = id;
        this.encomienda = encomienda;

        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.idEstado = idEstado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEncomienda() {
        return encomienda;
    }

    public void setEncomienda(String encomienda) {
        this.encomienda = encomienda;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaInicio) {
        this.fechaRegistro = fechaInicio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }



    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
}
