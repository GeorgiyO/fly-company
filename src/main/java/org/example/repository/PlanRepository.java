package org.example.repository;

import org.example.entity.Place;
import org.example.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PlanRepository extends DefaultRepository<Plan>, JpaRepository<Plan, Integer> {

    @Query(value = "call plan_get(:#{#id})", nativeQuery = true)
    Plan get(@Param("id") int id);

    @Query(value = "call plan_get_all()", nativeQuery = true)
    List<Plan> getAll();

    @Query(value = "call plan_remove(:#{#id})", nativeQuery = true)
    @Transactional
    @Modifying
    void delete(@Param("id") int id);

    @Query(value =
        "call plan_add(:#{#p.number}, :#{#p.kind}, :#{#p.workerId}, :#{#p.flyId})",
        nativeQuery = true
    )
    Plan add(@Param("p") Plan plan);

    @Query(value =
        "call plan_update(:#{#p.number}, :#{#p.kind}, :#{#p.workerId}, :#{#p.flyId}, :#{#id})",
        nativeQuery = true
    )
    Plan update(@Param("p") Plan plan, @Param("id") int id);

}
