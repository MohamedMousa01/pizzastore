package com.prova.pizzaStore.repository.cliente;

import com.prova.pizzaStore.model.Cliente;
import com.prova.pizzaStore.repository.ordine.CustomOrdineRepository;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long>, CustomOrdineRepository {



}
