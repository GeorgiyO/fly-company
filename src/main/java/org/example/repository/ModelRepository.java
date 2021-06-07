package org.example.repository;

import org.example.entity.Model;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ModelRepository extends DefaultRepository<Model> {
    public ModelRepository(JdbcTemplate jdbc) {
        super(jdbc, "model");
        super.setMapper(
            (rs, i) -> new Model().setId(rs.getInt("id"))
                                  .setName(rs.getString("name"))
        );
        super.setAddProps((m) -> new Object[]{
            m.getName()
        }, 1);
        super.setUpdateProps((id, m) -> new Object[]{
            id, m.getName()
        }, 2);
    }
}
