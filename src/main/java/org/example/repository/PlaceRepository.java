package org.example.repository;

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
public interface PlaceRepository extends DefaultRepository<Place>, JpaRepository<Place, Integer> {

    @Query(value = "call place_get(:#{#id})", nativeQuery = true)
    Place get(@Param("id") int id);

    @Query(value = "call place_get_all()", nativeQuery = true)
    List<Place> getAll();

    @Query(value = "call place_remove(:#{#id})", nativeQuery = true)
    @Transactional
    @Modifying
    void delete(@Param("id") int id);

    @Query(value =
        "call place_add(:#{#p.flyClassId}, :#{#p.number})",
        nativeQuery = true
    )
    Place add(@Param("p") Place place);

    @Query(value =
        "call place_update(:#{#p.flyClassId}, :#{#p.number}, :#{#id})",
        nativeQuery = true
    )
    Place update(@Param("p") Place place, @Param("id") int id);

}
