package com.prova.pizzaStore.repository.cliente;

import com.prova.pizzaStore.model.Cliente;
import com.prova.pizzaStore.model.Ordine;
import io.micrometer.common.util.StringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CostumClienteRepositoryImpl implements CostumClienteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cliente> findByExample(Cliente clienteExample) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select c from Cliente c where c.id = c.id ");

        if(StringUtils.isNotEmpty(clienteExample.getNome())){
            whereClauses.add(" c.nome like :nome");
            paramaterMap.put("nome", "%" + clienteExample.getNome() + "%");
        }
        if(StringUtils.isNotEmpty(clienteExample.getCognome())){
            whereClauses.add(" c.cognome like :cognome");
            paramaterMap.put("cognome", "%" + clienteExample.getCognome() +"%");
        }
        if(StringUtils.isNotEmpty(clienteExample.getIndirizzo())){
            whereClauses.add(" c.indirizzo like :indirizzo");
            paramaterMap.put("indirizzo", "%" + clienteExample.getIndirizzo() +"%");
        }
        if(clienteExample.getAttivo() != null) {
            whereClauses.add(" c.attivo = :attivo");
            paramaterMap.put("attivo", clienteExample.getAttivo());
        }

        queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
        queryBuilder.append(org.apache.commons.lang3.StringUtils.join(whereClauses, " and "));
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Cliente.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }

        return typedQuery.getResultList();
    }
}
