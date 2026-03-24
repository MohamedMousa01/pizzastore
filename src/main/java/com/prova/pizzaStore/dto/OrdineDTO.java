package com.prova.pizzaStore.dto;

import com.prova.pizzaStore.model.Ordine;
import com.prova.pizzaStore.model.Pizza;
import jakarta.validation.constraints.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class OrdineDTO {

    private Long id;

    @NotNull(message = "{ordine.dataordine.notnull}")
    private Date dataOrdine;


    private Boolean closed;

    @NotNull(message = "{ordine.costoTotale.notnull}")
    private Double costoTotale;


    private String codice;

    @NotNull(message = "{ordine.clienteDTO.notnull}")
    private ClienteDTO clienteDTO;

    @NotNull(message = "{ordine.listaPizze.notnull}")
    private Set<PizzaDTO> listaPizze = new HashSet<>();

    private Long[] pizzeIds;

    public OrdineDTO(){}


    public OrdineDTO(Date dataOrdine, Boolean closed, String codice, ClienteDTO clienteDTO, Set<PizzaDTO> listaPizze, Double costoTotale) {
        this.dataOrdine = dataOrdine;
        this.closed = closed;
        this.codice = codice;
        this.clienteDTO = clienteDTO;
        this.listaPizze = listaPizze;
        this.costoTotale = costoTotale;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public ClienteDTO getCliente() {
        return clienteDTO;
    }

    public void setCliente(ClienteDTO cliente) {
        this.clienteDTO = cliente;
    }

    public Set<PizzaDTO> getListaPizze() {
        return listaPizze;
    }

    public void setListaPizze(Set<PizzaDTO> listaPizze) {
        this.listaPizze = listaPizze;
    }

    public Double getCostoTotale() {
        return costoTotale;
    }

    public void setCostoTotale(Double costoTotale) {
        this.costoTotale = costoTotale;
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
    }

    public Long[] getPizzeIds() {
        return pizzeIds;
    }

    public void setPizzeIds(Long[] pizzeIds) {
        this.pizzeIds = pizzeIds;
    }

    public static OrdineDTO buildOrdineDTOFromModel(Ordine ordineModel, boolean includeClientiEPizze) {
        OrdineDTO result = new OrdineDTO();
        result.setId(ordineModel.getId());
        result.setDataOrdine(ordineModel.getDataOrdine());
        result.setClosed(ordineModel.getClosed());
        result.setCostoTotale(ordineModel.getCostoTotale());
        result.setCodice(ordineModel.getCodice());
        if(includeClientiEPizze) {
            if(ordineModel.getCliente() != null) {
                result.setClienteDTO(ClienteDTO.buildClienteDTOFromModel(ordineModel.getCliente()));
            }
            if (ordineModel.getPizze() != null && !ordineModel.getPizze().isEmpty()) {
                Long[] arrayIds = ordineModel.getPizze().stream()
                        .map(pizza -> pizza.getId())
                        .toArray(Long[]::new);
                result.setPizzeIds(arrayIds);
                result.setListaPizze(ordineModel.getPizze().stream()
                        .map(pizzaModel -> PizzaDTO.buildPizzaDTOFromModel(pizzaModel))
                        .collect(Collectors.toSet()));
            }
        }
        return result;
    }


    public Ordine buildOrdineFromDTO(){
        Ordine result = new Ordine();
        result.setId(this.getId());
        result.setDataOrdine(this.dataOrdine);
        result.setClosed(this.closed);
        result.setCostoTotale(this.costoTotale);
        result.setCodice(this.codice);
        if(this.clienteDTO != null) {
            result.setCliente(this.clienteDTO.buildClienteModel());
        }
        if (this.pizzeIds != null && this.pizzeIds.length > 0) {
            for (Long idPizza : this.pizzeIds) {
                Pizza pizzaModel = new Pizza();
                pizzaModel.setId(idPizza);
                result.getPizze().add(pizzaModel);
            }
        }
        return result;
    }

    public static List<OrdineDTO> createOrdineDTOListFromModelList(List<Ordine> modelListInput, boolean includeClientiEPizze) {
        if (modelListInput == null || modelListInput.isEmpty()) {
            return new ArrayList<>();
        }
        return modelListInput.stream().map(ordineEntity -> {
            return OrdineDTO.buildOrdineDTOFromModel(ordineEntity, includeClientiEPizze);
        }).collect(Collectors.toList());
    }


}
