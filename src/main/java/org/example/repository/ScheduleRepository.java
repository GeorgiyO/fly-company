package org.example.repository;

import org.example.entity.ScheduleItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepository extends DefaultRepository<ScheduleItem> {

    public ScheduleRepository(JdbcTemplate jdbc, PlanesRepository planesRepository, AddressRepository addressRepository) {
        super(jdbc, "schedule",
              (rs, i) -> new ScheduleItem().setId(rs.getInt("id"))
                                           .setDate(rs.getDate("date"))
                                           .setPrice(rs.getDouble("price"))
                                           .setPlane(planesRepository.get(rs.getInt("plane_id")))
                                           .setFromAddr(addressRepository.get(rs.getInt("from_addr_id")))
                                           .setToAddr(addressRepository.get(rs.getInt("to_addr_id"))),
              (i) -> new Object[]{i.getDate(), i.getPrice(), i.getPlane().getId(), i.getFromAddr().getId(), i.getToAddr().getId()},
              5
        );
    }

    public ScheduleItem update(int id, ScheduleItem i) {
        return one(
            "call schedule_update(?, ?, ?, ?, ?, ?)",
            id, i.getDate(), i.getPrice(), i.getPlane().getId(), i.getFromAddr().getId(), i.getToAddr().getId()
        );
    }

}
