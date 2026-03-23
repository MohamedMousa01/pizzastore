package com.prova.pizzaStore.service;

import com.prova.pizzaStore.model.Cliente;
import com.prova.pizzaStore.model.Pizza;
import com.prova.pizzaStore.repository.cliente.ClienteRepository;
import com.prova.pizzaStore.repository.pizza.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    PizzaRepository repository;

    @Transactional(readOnly = true)
    public List<Pizza> listAllElements() {
        return (List<Pizza>) repository.findAll();
    }

    @Transactional(readOnly = true)
    public Pizza caricaSingoloElemento(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void aggiorna(Pizza pizzaInstance) {
        repository.save(pizzaInstance);
    }

    @Transactional
    public void inserisciNuovo(Pizza pizzaInstance) {
        repository.save(pizzaInstance);
    }

    @Transactional
    public void rimuovi(Long idPizzaToDelete) {
        repository.deleteById(idPizzaToDelete);
    }

    @Transactional
    public List<Pizza> findByExample(Pizza example){
        return repository.findByExample(example);
    }

    @Override
    @Transactional
    public Pizza caricaSingoloPizza(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void disattivaPizza(Long id) {
        Pizza pizza = repository.findById(id).orElseThrow(() -> new RuntimeException("Pizza non trovata"));
        pizza.setAttivo(false);
        repository.save(pizza);
    }

    @Override
    public Pizza caricaSingoloElementoEager(Long id) {
        return null;
    }


}
