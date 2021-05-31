package org.example.repository;

import org.example.entity.Client;
import org.example.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ClientRepository extends DefaultRepository<Client>, JpaRepository<Client, Integer> {

    @Query(value = "call client_get(:#{#id})", nativeQuery = true)
    Client get(@Param("id") int id);

    @Query(value = "call client_get_all()", nativeQuery = true)
    List<Client> getAll();

    @Query(value = "call client_remove(:#{#id})", nativeQuery = true)
    @Transactional
    @Modifying
    void delete(@Param("id") int id);

    @Query(value =
        "call client_add(:#{#c.fio}, :#{#c.password}, :#{#c.ticketId}, :#{#c.cashId})",
        nativeQuery = true
    )
    Client add(@Param("c") Client client);

    @Query(value =
        "call client_update(:#{#c.fio}, :#{#c.password}, :#{#c.ticketId}, :#{#c.cashId}, :#{#id})",
        nativeQuery = true
    )
    Client update(@Param("c") Client client, @Param("id") int id);

}
