package org.example.repository;

import org.example.entity.Live;
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
public interface LiveRepository extends DefaultRepository<Live>, JpaRepository<Live, Integer> {

    @Query(value = "call live_get(:#{#id})", nativeQuery = true)
    Live get(@Param("id") int id);

    @Query(value = "call live_get_all()", nativeQuery = true)
    List<Live> getAll();

    @Query(value = "call live_remove(:#{#id})", nativeQuery = true)
    @Transactional
    @Modifying
    void delete(@Param("id") int id);

    @Query(value =
        "call live_add(:#{#l.name}, :#{#l.address})",
        nativeQuery = true
    )
    Live add(@Param("l") Live live);

    @Query(value =
        "call live_update(:#{#l.name}, :#{#l.address}, :#{#id})",
        nativeQuery = true
    )
    Live update(@Param("l") Live live, @Param("id") int id);

}