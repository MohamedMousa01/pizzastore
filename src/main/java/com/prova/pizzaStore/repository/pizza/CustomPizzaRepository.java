package com.prova.pizzaStore.repository.pizza;

import com.prova.pizzaStore.model.Ordine;
import com.prova.pizzaStore.model.Pizza;

import java.util.List;

public interface CustomPizzaRepository {

    public List<Pizza> findByExample(Pizza example);
}
