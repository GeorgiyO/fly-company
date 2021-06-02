package org.example.repository;

import org.example.entity.Passenger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PassengersRepository extends DefaultRepository<Passenger> {

    public PassengersRepository(JdbcTemplate jdbc) {
        super(jdbc, "passengers",
              (rs, i) -> new Passenger().setId(rs.getInt("id"))
                                        .setFirstName(rs.getString("first_name"))
                                        .setSecondName(rs.getString("second_name"))
                                        .setPatronymic(rs.getString("patronymic"))
                                        .setDocType(rs.getString("doc_type"))
                                        .setDocNumber(rs.getString("doc_number")),
              (p) -> new Object[]{p.getFirstName(), p.getSecondName(), p.getPatronymic(), p.getDocType(), p.getDocNumber()},
              5);
    }

    public Passenger update(int id, Passenger p) {
        return one(
            "call passengers_update(?, ?, ?, ?, ?, ?)",
            id, p.getFirstName(), p.getSecondName(), p.getPatronymic(), p.getDocType(), p.getDocNumber()
        );
    }

}
