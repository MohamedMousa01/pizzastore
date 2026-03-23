package com.prova.pizzaStore.dto;

import com.prova.pizzaStore.model.Cliente;
import com.prova.pizzaStore.model.Ordine;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteDTO {

    private Long id;

    @NotBlank(message = "{cliente.nome.notblank}")
    private String nome;

    @NotNull(message = "{cliente.cognome.notnull}")
    private String cognome;

    @NotNull(message = "{cliente.indirizzo.notnull}")
    private String indirizzo;

    private Boolean attivo;

    private Set<OrdineDTO> ordini = new HashSet<>();

    public ClienteDTO(){}

    public ClienteDTO(Long id, String nome, String cognome, String indirizzo, Boolean attivo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.attivo = attivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public Set<OrdineDTO> getOrdini() {
        return ordini;
    }

    public void setOrdini(Set<OrdineDTO> ordini) {
        this.ordini = ordini;
    }

    public Cliente buildClienteModel() {
        Cliente result = new Cliente();
        result.setId(this.id);
        result.setNome(this.nome);
        result.setCognome(this.cognome);
        result.setIndirizzo(this.indirizzo);
        result.setAttivo(this.attivo);
        if(this.getOrdini() != null && !this.getOrdini().isEmpty()){
            Set<Ordine> ordini = this.ordini.stream()
                    .map(OrdineDTO::buildOrdineFromDTO)
                    .collect(Collectors.toSet());
            result.setOrdini(ordini);
        }
        return result;
    }

    public static ClienteDTO buildClienteDTOFromModel(Cliente cliente){
        ClienteDTO result = new ClienteDTO();
        result.setId(cliente.getId());
        result.setNome(cliente.getNome());
        result.setCognome(cliente.getCognome());
        result.setIndirizzo(cliente.getIndirizzo());
        result.setAttivo(cliente.getAttivo());
        if (cliente.getOrdini() != null && !cliente.getOrdini().isEmpty()) {
            result.setOrdini(cliente.getOrdini().stream()
                    .map(ordineModel -> OrdineDTO.buildOrdineDTOFromModel(ordineModel, false))
                    .collect(Collectors.toSet()));
        }
        return result;
    }

    public static List<ClienteDTO> createClienteDTOListFromModelList(List<Cliente> listInput){
        return listInput.stream().map(clienteEntity ->{
            return ClienteDTO.buildClienteDTOFromModel(clienteEntity);
        }).collect(Collectors.toList());
    }
}
