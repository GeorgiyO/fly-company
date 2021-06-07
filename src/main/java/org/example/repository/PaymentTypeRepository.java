package org.example.repository;

import org.example.entity.PaymentType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentTypeRepository extends DefaultRepository<PaymentType> {
    public PaymentTypeRepository(JdbcTemplate jdbc) {
        super(jdbc, "payment_type");
        super.setMapper(
            (rs, i) -> new PaymentType().setId(rs.getInt("id"))
                                        .setType(rs.getString("type"))
        );
        super.setAddProps((pt) -> new Object[]{
            pt.getType()
        }, 1);
        super.setUpdateProps((id, pt) -> new Object[]{
            id, pt.getType()
        }, 2);
    }
}
