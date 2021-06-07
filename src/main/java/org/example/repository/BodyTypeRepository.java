package org.example.repository;

import org.example.entity.BodyType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BodyTypeRepository extends DefaultRepository<BodyType> {
    public BodyTypeRepository(JdbcTemplate jdbc) {
        super(jdbc, "body_type");
        super.setMapper(
            (rs, i) -> new BodyType().setId(rs.getInt("id"))
                                     .setType(rs.getString("type"))
        );
        super.setAddProps((bt) -> new Object[]{
            bt.getType()
        }, 1);
        super.setUpdateProps((id, bt) -> new Object[]{
            id, bt.getType()
        }, 2);
    }
}
