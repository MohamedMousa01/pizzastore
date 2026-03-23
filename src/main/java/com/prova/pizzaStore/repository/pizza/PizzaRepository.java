package com.prova.pizzaStore.repository.pizza;

import com.prova.pizzaStore.model.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza, Long>, CustomPizzaRepository{



}
