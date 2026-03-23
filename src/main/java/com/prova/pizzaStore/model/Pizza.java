package com.prova.pizzaStore.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "ingredienti")
    private String ingredienti;

    @Column(name = "prezzo")
    private Double prezzo;

    @Column(name = "attivo")
    private Boolean attivo;

    @ManyToMany(mappedBy = "pizze")
    private Set<Ordine> ordini = new HashSet<>();


    public Pizza(){}

    public Pizza(Long id, String descrizione, String ingredienti, Double prezzo, Boolean attivo, Set<Ordine> ordini) {
        this.id = id;
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.prezzo = prezzo;
        this.attivo = attivo;
        this.ordini = ordini;
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

    public Set<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(Set<Ordine> ordini) {
        this.ordini = ordini;
    }
}
