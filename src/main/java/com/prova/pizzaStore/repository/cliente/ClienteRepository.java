package com.prova.pizzaStore.repository.cliente;

import com.prova.pizzaStore.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends CrudRepository<Cliente, Long>, CostumClienteRepository {

    @Query("select c from Cliente c left join fetch c.ordini where c.id = :id")
    Cliente findClienteEager(@Param("id") Long id);
}
