package org.example.repository;

import org.example.entity.Place;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlacesRepository extends DefaultRepository<Place> {

    public PlacesRepository(JdbcTemplate jdbc, ScheduleRepository scheduleRepository) {
        super(jdbc, "places",
              (rs, i) -> new Place().setId(rs.getInt("id"))
                                    .setTotal(rs.getInt("total"))
                                    .setFree(rs.getInt("free"))
                                    .setScheduleItem(scheduleRepository.get(rs.getInt("schedule_id"))),
              (p) -> new Object[]{p.getTotal(), p.getScheduleItem().getId()},
              2);
    }

    public List<Place> free() {
        return jdbc.query(
            "call places_free()",
            mapper
        );
    }

}
