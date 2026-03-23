package com.prova.pizzaStore.service;

import com.prova.pizzaStore.model.Cliente;
import com.prova.pizzaStore.repository.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository repository;

    @Transactional(readOnly = true)
    public List<Cliente> listAllElements() {
        return (List<Cliente>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Cliente caricaSingoloElemento(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void aggiorna(Cliente clienteInstance) {
        repository.save(clienteInstance);
    }

    @Transactional
    public void inserisciNuovo(Cliente clienteInstance) {
        repository.save(clienteInstance);
    }

    @Transactional
    public void rimuovi(Long idClienteToDelete) {
        repository.deleteById(idClienteToDelete);
    }

   @Transactional
    public List<Cliente> findByExample(Cliente example){
        return repository.findByExample(example);
   }



}
