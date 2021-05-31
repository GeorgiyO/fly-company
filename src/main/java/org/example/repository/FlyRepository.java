package org.example.repository;

import org.example.entity.Fly;
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
public interface FlyRepository extends DefaultRepository<Fly>, JpaRepository<Fly, Integer> {

    @Query(value = "call fly_get(:#{#id})", nativeQuery = true)
    Fly get(@Param("id") int id);

    @Query(value = "call fly_get_all()", nativeQuery = true)
    List<Fly> getAll();

    @Query(value = "call fly_remove(:#{#id})", nativeQuery = true)
    @Transactional
    @Modifying
    void delete(@Param("id") int id);

    @Query(value =
        "call fly_add(:#{#f.number}, :#{#f.date}, :#{#f.arriveId}, :#{#f.liveId})",
        nativeQuery = true
    )
    Fly add(@Param("f") Fly fly);

    @Query(value =
        "call fly_update(:#{#f.number}, :#{#f.date}, :#{#f.arriveId}, :#{#f.liveId}, :#{#id})",
        nativeQuery = true
    )
    Fly update(@Param("f") Fly fly, @Param("id") int id);

}