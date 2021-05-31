package org.example.repository;

import org.example.entity.Pilot;
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
public interface PilotRepository extends DefaultRepository<Pilot>, JpaRepository<Pilot, Integer> {

    @Query(value = "call pilot_get(:#{#id})", nativeQuery = true)
    Pilot get(@Param("id") int id);

    @Query(value = "call pilot_get_all()", nativeQuery = true)
    List<Pilot> getAll();

    @Query(value = "call pilot_remove(:#{#id})", nativeQuery = true)
    @Transactional
    @Modifying
    void delete(@Param("id") int id);

    @Query(value =
        "call pilot_add(:#{#p.fio}, :#{#p.tel}, :#{#p.planId})",
        nativeQuery = true
    )
    Pilot add(@Param("p") Pilot pilot);

    @Query(value =
        "call pilot_update(:#{#p.fio}, :#{#p.tel}, :#{#p.planId}, :#{#id})",
        nativeQuery = true
    )
    Pilot update(@Param("p") Pilot pilot, @Param("id") int id);

}
