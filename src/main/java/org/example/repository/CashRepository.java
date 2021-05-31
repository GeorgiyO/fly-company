package org.example.repository;

import org.example.entity.Cash;
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
public interface CashRepository extends DefaultRepository<Cash>, JpaRepository<Cash, Integer> {

    @Query(value = "call cash_get(:#{#id})", nativeQuery = true)
    Cash get(@Param("id") int id);

    @Query(value = "call cash_get_all()", nativeQuery = true)
    List<Cash> getAll();

    @Query(value = "call cash_remove(:#{#id})", nativeQuery = true)
    @Transactional
    @Modifying
    void delete(@Param("id") int id);

    @Query(value =
        "call cash_add(:#{#c.number})",
        nativeQuery = true
    )
    Cash add(@Param("c") Cash cash);

    @Query(value =
        "call cash_update(:#{#c.number}, :#{#id})",
        nativeQuery = true
    )
    Cash update(@Param("c") Cash cash, @Param("id") int id);

}
