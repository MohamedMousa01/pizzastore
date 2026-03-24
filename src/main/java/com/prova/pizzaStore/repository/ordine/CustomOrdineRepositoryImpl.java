package com.prova.pizzaStore.repository.ordine;

import com.prova.pizzaStore.model.Ordine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomOrdineRepositoryImpl implements CustomOrdineRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Ordine> findByExample(Ordine example){
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select o from Ordine o where o.id = o.id ");

        if(StringUtils.isNoneBlank(example.getCodice())){
            whereClauses.add(" o.codice = :codice");
            paramaterMap.put("codice", example.getCodice());
        }
        if (example.getClosed() != null) {
            whereClauses.add(" o.closed like :closed ");
            paramaterMap.put("closed", "%" + example.getClosed() + "%");
        }
        if (example.getDataOrdine() != null) {
            whereClauses.add(" o.dataOrdine like :dataordine ");
            paramaterMap.put("dataOrdine", "%" + example.getDataOrdine() + "%");
        }
        if (example.getCostoTotale() != null) {
            whereClauses.add(" o.costoTotale =:minutiDurata ");
            paramaterMap.put("costoTotale", example.getCostoTotale());
        }


        queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));
        TypedQuery<Ordine> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ordine.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }

        return typedQuery.getResultList();
    }


    @Override
    public List<Ordine> findByDataOrdineBetween(LocalDateTime dataInizio, LocalDateTime dataFine) {

        StringBuilder queryBuilder = new StringBuilder("select o from Ordine o where o.id = o.id ");
        Map<String, Object> paramMap = new HashMap<>();

        if (dataInizio != null) {
            queryBuilder.append(" and o.dataOrdine >= :dataInizio ");
            paramMap.put("dataInizio", dataInizio);
        }

        if (dataFine != null) {
            queryBuilder.append(" and o.dataOrdine <= :dataFine ");
            paramMap.put("dataFine", dataFine);
        }

        TypedQuery<Ordine> query = entityManager.createQuery(queryBuilder.toString(), Ordine.class);
        paramMap.forEach(query::setParameter);

        return query.getResultList();
    }
}
