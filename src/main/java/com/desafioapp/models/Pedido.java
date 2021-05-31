package com.desafioapp.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//@OneToOne
	private int id_cliente;
	
	private int total_compra;
	
	//@DateTimeFormat(pattern = "dd-MM-yyyy")
	private String data;
	
	@OneToMany
	private List<Produto> produtos;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTotal_compra() {
		return total_compra;
	}

	public void setTotal_compra(int total_compra) {
		this.total_compra = total_compra;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	
}
