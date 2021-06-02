package org.example.repository;

import org.example.entity.Address;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class AddressRepository extends DefaultRepository<Address> {

    final JdbcTemplate jdbc;

    public AddressRepository(JdbcTemplate jdbc) {
        super(jdbc, "address",
              (ResultSet rs, int i) -> new Address().setId(rs.getInt("id"))
                                                    .setValue(rs.getString("value")),
              (a) -> new Object[]{a.getValue()},
              1
        );
        this.jdbc = jdbc;
    }

    public Address update(int id, Address a) {
        return one(
            "call address_update(?, ?)",
            id,
            a.getValue()
        );
    }

}
