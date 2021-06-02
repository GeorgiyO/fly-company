package org.example.repository;

import org.example.entity.Plane;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlanesRepository extends DefaultRepository<Plane> {

    public PlanesRepository(JdbcTemplate jdbc) {
        super(jdbc, "planes",
              (rs, i) -> new Plane().setId(rs.getInt("id"))
                                    .setDescription(rs.getString("description")),
              (p) -> new Object[]{p.getDescription()},
              1);
    }

    public Plane update(int id, Plane p) {
        return one("call planes_update(?, ?)", id, p.getDescription());
    }

}
