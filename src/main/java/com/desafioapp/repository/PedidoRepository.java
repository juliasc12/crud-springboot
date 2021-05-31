package com.desafioapp.repository;
import org.springframework.data.repository.CrudRepository;

import com.desafioapp.models.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, String>{
	Pedido findById(long id);
}
