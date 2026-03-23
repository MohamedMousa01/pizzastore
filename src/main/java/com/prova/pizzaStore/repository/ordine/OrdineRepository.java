package com.prova.pizzaStore.repository.ordine;

import com.prova.pizzaStore.model.Ordine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdineRepository extends CrudRepository<Ordine, Long>, CustomOrdineRepository{

    @Query("select o from Ordine o left join fetch o.cliente")
    List<Ordine> findAllOrdiniEager();

    @Query("select o from Ordine o left join fetch o.cliente left join fetch o.pizze where o.id = :id")
    Ordine findByIdEager(@Param("id") Long id);
}
