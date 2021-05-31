package com.desafioapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.desafioapp.models.Cliente;

public interface ClienteRepository extends CrudRepository <Cliente, String>{
	Cliente findById(long id);
	
}
