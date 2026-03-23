package com.prova.pizzaStore.repository.ordine;

import com.prova.pizzaStore.model.Ordine;
import org.springframework.data.repository.CrudRepository;

public interface OrdineRepository extends CrudRepository<Ordine, Long>, CustomOrdineRepository{


}
