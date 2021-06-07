package org.example.repository;

import org.example.entity.Brand;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandRepository extends DefaultRepository<Brand> {
    public BrandRepository(JdbcTemplate jdbc) {
        super(jdbc, "brand");
        super.setMapper(
            (rs, i) -> new Brand().setId(rs.getInt("id"))
                                  .setName(rs.getString("name"))
        );
        super.setAddProps((b) -> new Object[]{
            b.getName()
        }, 1);
        super.setUpdateProps((id, b) -> new Object[]{
            id, b.getName()
        }, 2);
    }

    public Brand withSales(int id) {
        return super.one(
            "call brand_with_sales(?)",
            id
        );
    }

    public Double salesSum() {
        return super.jdbc.query(
            "call brands_sales_total()",
            (rs, i) -> rs.getDouble("sales")
        ).get(0);
    }

}
