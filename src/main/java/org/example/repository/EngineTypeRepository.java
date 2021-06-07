package org.example.repository;

import org.example.entity.EngineType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EngineTypeRepository extends DefaultRepository<EngineType> {
    public EngineTypeRepository(JdbcTemplate jdbc) {
        super(jdbc, "engine_type");
        super.setMapper(
            (rs, i) -> new EngineType().setId(rs.getInt("id"))
                                       .setType(rs.getString("type"))
        );
        super.setAddProps((et) -> new Object[]{
            et.getType()
        }, 1);
        super.setUpdateProps((id, et) -> new Object[]{
            id, et.getType()
        }, 2);
    }
}
