package org.example.repository;

import org.example.entity.Country;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CountryRepository extends DefaultRepository<Country> {
    public CountryRepository(JdbcTemplate jdbc) {
        super(jdbc, "country");
        super.setMapper(
            (rs, i) -> new Country().setId(rs.getInt("id"))
                                    .setName(rs.getString("name"))
        );
        super.setAddProps((c) -> new Object[]{
            c.getName()
        }, 1);
        super.setUpdateProps((id, c) -> new Object[]{
            id, c.getName()
        }, 2);
    }
}
