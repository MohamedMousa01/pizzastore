package com.prova.pizzaStore.repository.pizza;

import com.prova.pizzaStore.model.Ordine;
import com.prova.pizzaStore.model.Pizza;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomPizzaRepositoryImpl implements CustomPizzaRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Pizza> findByExample(Pizza example){
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select p from Pizza o where p.id = op.id ");

        if(StringUtils.isNoneBlank(example.getDescrizione())){
            whereClauses.add(" o.descrizione = :descrizione");
            paramaterMap.put("descrizione", example.getDescrizione());
        }
        if(StringUtils.isNotEmpty(example.getIngredienti())){
            whereClauses.add(" p.ingredienti like :ingredienti");
            paramaterMap.put("ingredienti", "%" + example.getIngredienti() +"%");
        }
        if (example.getAttivo() != null) {
            whereClauses.add(" p.attivo like :attivo ");
            paramaterMap.put("attivo", "%" + example.getAttivo() + "%");
        }
        if (example.getPrezzo() != null) {
            whereClauses.add(" o.prezzo =:prezzo ");
            paramaterMap.put("prezzo", example.getPrezzo());
        }


        queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));
        TypedQuery<Pizza> typedQuery = entityManager.createQuery(queryBuilder.toString(), Pizza.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }

        return typedQuery.getResultList();
    }
}
