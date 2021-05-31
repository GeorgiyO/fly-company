package org.example.repository;

import org.example.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TicketRepository extends DefaultRepository<Ticket>, JpaRepository<Ticket, Integer> {

    @Query(value = "call ticket_get(:#{#id})", nativeQuery = true)
    Ticket get(@Param("id") int id);

    @Query(value = "call ticket_get_all()", nativeQuery = true)
    List<Ticket> getAll();

    @Query(value = "call ticket_remove(:#{#id})", nativeQuery = true)
    @Transactional
    @Modifying
    void delete(@Param("id") int id);

    @Query(value =
        "call ticket_add(:#{#t.number}, :#{#t.price}, :#{#t.cashId}, :#{#t.planId})",
        nativeQuery = true
    )
    Ticket add(@Param("t") Ticket ticket);

    @Query(value =
        "call ticket_update(:#{#t.number}, :#{#t.price}, :#{#t.cashId}, :#{#t.planId}, :#{#id})",
        nativeQuery = true
    )
    Ticket update(@Param("t") Ticket ticket, @Param("id") int id);

}
