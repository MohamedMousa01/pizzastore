package com.prova.pizzaStore.service;

import com.prova.pizzaStore.model.Cliente;
import com.prova.pizzaStore.model.Ordine;
import com.prova.pizzaStore.model.Pizza;
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

    @Override
    public double calcolaPrezzoOrdine(Long idOrdine) {
        Ordine ordine = repository.findByIdEager(idOrdine);
        double result = 0.0;
        if(ordine == null || ordine.getPizze() == null || ordine.getPizze().isEmpty()){
            throw new RuntimeException("Questo ordine non esiste o non ci sono pizze ordinate.");
        }
        for(Pizza pizza: ordine.getPizze()){
            result += pizza.getPrezzo();
        }
        result *= 1.15;
        ordine.setCostoTotale(result);
        repository.save(ordine);
        return result;
    }
}
