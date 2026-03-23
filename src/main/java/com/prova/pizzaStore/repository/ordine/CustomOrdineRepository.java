package com.prova.pizzaStore.repository.ordine;

import com.prova.pizzaStore.model.Ordine;

import java.util.List;

public interface CustomOrdineRepository {

    public List<Ordine> findByExample(Ordine example);
}
