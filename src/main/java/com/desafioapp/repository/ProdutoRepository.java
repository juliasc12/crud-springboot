package com.desafioapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.desafioapp.models.Pedido;
import com.desafioapp.models.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, String>{
	Iterable<Produto> findByPedido(Pedido pedido);
	Produto findByNome(String nome);
}
