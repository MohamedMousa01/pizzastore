package com.prova.pizzaStore.dto;

import com.prova.pizzaStore.model.Pizza;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;


public class PizzaDTO {

    private Long id;

    @NotNull(message = "{pizza.descrizione.notnull}")
    private String descrizione;

    @NotNull(message = "{pizza.ingredienti.notnull}")
    private String ingredienti;

    @NotNull(message = "{pizza.prezzo.notnull}")
    private Double prezzo;

    private Boolean attivo;




    public PizzaDTO(){}

    public PizzaDTO(Long id, String descrizione, String ingredienti, Double prezzo, Boolean attivo) {
        this.id = id;
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.prezzo = prezzo;
        this.attivo = attivo;
    }

    public PizzaDTO(String descrizione, String ingredienti, Double prezzo, Boolean attivo) {
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

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
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

    public static List<PizzaDTO> createPizzaDTOListFromModelList(List<Pizza> listInput){
        return listInput.stream().map(pizzaEntity ->{
            return PizzaDTO.buildPizzaDTOFromModel(pizzaEntity);
        }).collect(Collectors.toList());
    }

    public Pizza buildModelFromDTO(){
        Pizza pizza = new Pizza();
        pizza.setId(this.id);
        pizza.setDescrizione(this.descrizione);
        pizza.setAttivo(this.attivo);
        pizza.setPrezzo(this.prezzo);
        pizza.setIngredienti(this.ingredienti);
        return pizza;
    }

}



