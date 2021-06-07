package org.example.repository;

import org.example.entity.Brand;
import org.example.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository extends DefaultRepository<Product> {
    public ProductRepository(JdbcTemplate jdbc, ModelRepository modelRepository, CountryRepository countryRepository, BrandRepository brandRepository, SpecificationRepository specificationRepository) {
        super(jdbc, "product");
        super.setMapper(
            (rs, i) -> new Product().setCode(rs.getInt("code"))
                                    .setAvailable(rs.getInt("available") == 1)
                                    .setAvailabilityDate(rs.getDate("availability_date"))
                                    .setPrice(rs.getDouble("price"))
                                    .setModel(modelRepository.get(rs.getInt("model_id")))
                                    .setCountry(countryRepository.get(rs.getInt("country_id")))
                                    .setBrand(brandRepository.get(rs.getInt("brand_id")))
                                    .setSpecification(specificationRepository.get(rs.getInt("specification_id")))
        );
        super.setAddProps((p) -> new Object[]{
            p.isAvailable() ? 1 : 0,
            p.getAvailabilityDate(),
            p.getPrice(),
            p.getModel().getId(),
            p.getCountry().getId(),
            p.getBrand().getId(),
            p.getSpecification().getId()
        }, 7);
        super.setUpdateProps((id, p) -> new Object[]{
            id,
            p.isAvailable() ? 1 : 0,
            p.getAvailabilityDate(),
            p.getPrice(),
            p.getModel().getId(),
            p.getCountry().getId(),
            p.getBrand().getId(),
            p.getSpecification().getId()
        }, 8);
    }

    public List<Product> searchByBrandAndModel(int brandId, int modelId) {
        return super.jdbc.query(
            "call product_search_brand_model(?, ?)",
            super.mapper,
            brandId, modelId
        );
    }
}
