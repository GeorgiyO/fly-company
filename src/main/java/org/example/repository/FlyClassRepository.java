package org.example.repository;

import org.example.entity.FlyClass;
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
public interface FlyClassRepository extends DefaultRepository<FlyClass>, JpaRepository<FlyClass, Integer> {

    @Query(value = "call class_get(:#{#id})", nativeQuery = true)
    FlyClass get(@Param("id") int id);

    @Query(value = "call class_get_all()", nativeQuery = true)
    List<FlyClass> getAll();

    @Query(value = "call class_remove(:#{#id})", nativeQuery = true)
    @Modifying
    @Transactional
    void delete(@Param("id") int id);

    @Query(value =
        "call class_add(:#{#c.name}, :#{#c.planId})",
        nativeQuery = true
    )
    FlyClass add(@Param("c") FlyClass flyClass);

    @Query(value =
        "call class_update(:#{#c.name}, :#{#c.planId}, :#{#id})",
        nativeQuery = true
    )
    FlyClass update(@Param("c") FlyClass flyClass, @Param("id") int id);

}
