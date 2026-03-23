package com.prova.pizzaStore.service;

import com.prova.pizzaStore.model.Cliente;
import com.prova.pizzaStore.model.Ordine;
import com.prova.pizzaStore.repository.ordine.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdineServiceImpl implements OrdineService{

    @Autowired
    OrdineRepository repository;

    @Transactional(readOnly = true)
    public List<Ordine> listAllElements() {
        return (List<Ordine>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Ordine caricaSingoloElemento(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void aggiorna(Ordine ordineInstance) {
        repository.save(ordineInstance);
    }

    @Transactional
    public void inserisciNuovo(Ordine ordineInstance) {
        repository.save(ordineInstance);
    }

    @Transactional
    public void rimuovi(Long idOrdineToDelete) {
        repository.deleteById(idOrdineToDelete);
    }

    @Transactional
    public List<Ordine> findByExample(Ordine example){
        return repository.findByExample(example);
    }

    @Override
    public Ordine caricaSingoloElementoEager(Long id) {
        return repository.findByIdEager(id);
    }


}
