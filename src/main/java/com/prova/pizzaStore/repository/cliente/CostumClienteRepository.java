package com.prova.pizzaStore.repository.cliente;

import com.prova.pizzaStore.model.Cliente;

import java.util.List;

public interface CostumClienteRepository {

    public List<Cliente> findByExample(Cliente clienteExample);
}
