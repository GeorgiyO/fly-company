package org.example.repository;

import org.example.entity.Place;
import org.example.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface WorkerRepository extends DefaultRepository<Worker>, JpaRepository<Worker, Integer> {

    @Query(value = "call worker_get(:#{#id})", nativeQuery = true)
    Worker get(@Param("id") int id);

    @Query(value = "call worker_get_all()", nativeQuery = true)
    List<Worker> getAll();

    @Query(value = "call worker_remove(:#{#id})", nativeQuery = true)
    @Transactional
    @Modifying
    void delete(@Param("id") int id);

    @Query(value =
        "call worker_add(:#{#w.fio}, :#{#w.tel})",
        nativeQuery = true
    )
    Worker add(@Param("w") Worker worker);

    @Query(value =
        "call worker_update(:#{#w.fio}, :#{#w.tel}, :#{#id})",
        nativeQuery = true
    )
    Worker update(@Param("w") Worker worker, @Param("id") int id);

}
