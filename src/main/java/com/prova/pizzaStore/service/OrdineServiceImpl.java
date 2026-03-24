package com.prova.pizzaStore.service;

import com.prova.pizzaStore.dto.ClienteDTO;
import com.prova.pizzaStore.dto.StatsDTO;
import com.prova.pizzaStore.model.Cliente;
import com.prova.pizzaStore.model.Ordine;
import com.prova.pizzaStore.model.Pizza;
import com.prova.pizzaStore.repository.ordine.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Override
    public StatsDTO calcolaStatistiche(LocalDate dataInizio, LocalDate dataFine) {

        List<Ordine> ordini = repository.findByDataOrdineBetween(
                dataInizio != null ? dataInizio.atStartOfDay(): null,
                dataFine != null ? dataFine.atTime(23,59,59): null
        );

        StatsDTO dto = new StatsDTO();

        // 1. Ricavi totali
        double ricavi = ordini.stream()
                .map(Ordine::getCostoTotale)
                .filter(Objects::nonNull)
                .reduce(0.0, Double::sum);

        dto.setRicaviTotali(ricavi);

        // 2. Costi totali (es: somma prezzi base pizze)
        double costi = ordini.stream()
                .flatMap(o -> o.getPizze().stream())
                .map(Pizza::getPrezzo)
                .reduce(0.0, Double::sum);

        dto.setCostiTotali(costi);

        // 3. Numero ordini
        dto.setOrdiniTotali((long) ordini.size());

        // 4. Numero pizze
        long numeroPizze = ordini.stream()
                .flatMap(o -> o.getPizze().stream())
                .count();

        dto.setOrdiniTotali(numeroPizze);

        // 5. Clienti virtuosi (>100€)
        List<ClienteDTO> clientiVirtuosi = ordini.stream()
                .filter(o -> o.getCostoTotale() != null && o.getCostoTotale() > 100)
                .map(Ordine::getCliente)
                .distinct()
                .map(ClienteDTO::buildClienteDTOFromModel)
                .toList();

        dto.setClientiVirtuosi(clientiVirtuosi);

        return dto;
    }



}
