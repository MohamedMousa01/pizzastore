package com.prova.pizzaStore.repository.ordine;

import com.prova.pizzaStore.model.Ordine;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CustomOrdineRepository {

    public List<Ordine> findByExample(Ordine example);
    List<Ordine> findByDataOrdineBetween(LocalDateTime start, LocalDateTime end);
}
