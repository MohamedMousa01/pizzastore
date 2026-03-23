package com.prova.pizzaStore.service;

import com.prova.pizzaStore.model.Cliente;
import com.prova.pizzaStore.model.Ordine;


import java.util.List;

public interface OrdineService {

    public List<Ordine> listAllElements();

    public Ordine caricaSingoloElemento(Long id);

   public Ordine caricaSingoloElementoEager(Long id);

    public void aggiorna(Ordine ordineInstance);

    public void inserisciNuovo(Ordine ordineInstance);

    public void rimuovi(Long idOrdineToDelete);

    public List<Ordine> findByExample(Ordine example);

    public double calcolaPrezzoOrdine(Long idOrdine);

}
