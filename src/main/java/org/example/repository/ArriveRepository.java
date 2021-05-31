package org.example.repository;

import org.example.entity.Arrive;
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
public interface ArriveRepository extends DefaultRepository<Arrive>, JpaRepository<Arrive, Integer> {

    @Query(value = "call arrive_get(:#{#id})", nativeQuery = true)
    Arrive get(@Param("id") int id);

    @Query(value = "call arrive_get_all()", nativeQuery = true)
    List<Arrive> getAll();

    @Query(value = "call arrive_remove(:#{#id})", nativeQuery = true)
    @Transactional
    @Modifying
    void delete(@Param("id") int id);

    @Query(value =
        "call arrive_add(:#{#a.name}, :#{#a.address})",
        nativeQuery = true
    )
    Arrive add(@Param("a") Arrive arrive);

    @Query(value =
        "call arrive_update(:#{#a.name}, :#{#a.address}, :#{#id})",
        nativeQuery = true
    )
    Arrive update(@Param("a") Arrive arrive, @Param("id") int id);

    @PostConstruct
    default void setRepo() {
        Arrive.repo = this;
    }

}