package com.prova.pizzaStore.dto;

import com.prova.pizzaStore.model.Pizza;
import jakarta.validation.constraints.NotNull;


public class PizzaDTO {

    private Long id;

    @NotNull(message = "{pizza.descrizione.notnull}")
    private String descrizione;

    @NotNull(message = "{pizza.ingredienti.notnull}")
    private String ingredienti;

    @NotNull(message = "{pizza.prezzo.notnull}")
    private float prezzo;

    @NotNull(message = "{pizza.attivo.notnull}")
    private Boolean attivo;

    public PizzaDTO(){}

    public PizzaDTO(Long id, String descrizione, String ingredienti, float prezzo, Boolean attivo) {
        this.id = id;
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.prezzo = prezzo;
        this.attivo = attivo;
    }

    public PizzaDTO(String descrizione, String ingredienti, float prezzo, Boolean attivo) {
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.prezzo = prezzo;
        this.attivo = attivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(String ingredienti) {
        this.ingredienti = ingredienti;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public static PizzaDTO buildPizzaDTOFromModel(Pizza pizza){
        return new PizzaDTO(pizza.getId(), pizza.getDescrizione(), pizza.getIngredienti(), pizza.getPrezzo(), pizza.getAttivo());
    }
}
