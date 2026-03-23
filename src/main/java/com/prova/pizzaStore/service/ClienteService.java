package com.prova.pizzaStore.service;

import com.prova.pizzaStore.model.Cliente;


import java.util.List;


public interface ClienteService {

    public List<Cliente> listAllElements();

    public Cliente caricaSingoloCliente(Long id);

    public void aggiorna(Cliente clienteInstance);

    public void inserisciNuovo(Cliente clienteInstance);

    public void rimuovi(Long idClienteToDelete);

    public List<Cliente> findByExample(Cliente example);

    public Cliente caricaSingoloElementoEager(Long id);

    public void disattivaCliente(Long id);
}
