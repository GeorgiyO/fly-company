package org.example.repository;

import org.example.entity.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpecificationRepository extends DefaultRepository<Specification> {
    public SpecificationRepository(JdbcTemplate jdbc, BodyTypeRepository bodyTypeRepository, EngineTypeRepository engineTypeRepository) {
        super(jdbc, "specification");
        super.setMapper(
            (rs, i) -> new Specification().setId(rs.getInt("id"))
                                          .setDoorsCount(rs.getInt("doors_count"))
                                          .setSeatsCount(rs.getInt("seats_count"))
                                          .setEngineWorkingVolume(rs.getDouble("engine_working_volume"))
                                          .setEnginePos(rs.getString("engine_pos"))
                                          .setBodyType(bodyTypeRepository.get(rs.getInt("body_type_id")))
                                          .setEngineType(engineTypeRepository.get(rs.getInt("engine_type_id")))
        );
        super.setAddProps((s) -> new Object[]{
            s.getDoorsCount(),
            s.getSeatsCount(),
            s.getEngineWorkingVolume(),
            s.getEnginePos(),
            s.getBodyType().getId(),
            s.getEngineType().getId()
        }, 6);
        super.setUpdateProps((id, s) -> new Object[]{
            id,
            s.getDoorsCount(),
            s.getSeatsCount(),
            s.getEngineWorkingVolume(),
            s.getEnginePos(),
            s.getBodyType().getId(),
            s.getEngineType().getId()
        }, 7);
    }
}
