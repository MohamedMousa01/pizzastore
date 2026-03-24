package com.prova.pizzaStore.dto;

import java.util.List;

public class StatsDTO {

    private double ricaviTotali;
    private double costiTotali;
    private Long ordiniTotali;
    private Long pizzaTotali;
    private List<ClienteDTO> clientiVirtuosi;

    public StatsDTO(){};

    public StatsDTO(double ricaviTotali, double costiTotali, Long ordiniTotali, Long pizzaTotali, List<ClienteDTO> clienteDTO) {
        this.ricaviTotali = ricaviTotali;
        this.costiTotali = costiTotali;
        this.ordiniTotali = ordiniTotali;
        this.pizzaTotali = pizzaTotali;
        this.clientiVirtuosi = clienteDTO;
    }

    public double getRicaviTotali() {
        return ricaviTotali;
    }

    public void setRicaviTotali(double ricaviTotali) {
        this.ricaviTotali = ricaviTotali;
    }

    public double getCostiTotali() {
        return costiTotali;
    }

    public void setCostiTotali(double costiTotali) {
        this.costiTotali = costiTotali;
    }

    public Long getOrdiniTotali() {
        return ordiniTotali;
    }

    public void setOrdiniTotali(Long ordiniTotali) {
        this.ordiniTotali = ordiniTotali;
    }

    public Long getPizzaTotali() {
        return pizzaTotali;
    }

    public void setPizzaTotali(Long pizzaTotali) {
        this.pizzaTotali = pizzaTotali;
    }

    public List<ClienteDTO> getClientiVirtuosi() {
        return clientiVirtuosi;
    }

    public void setClientiVirtuosi(List<ClienteDTO> clientiVirtuosi) {
        this.clientiVirtuosi = clientiVirtuosi;
    }


}
