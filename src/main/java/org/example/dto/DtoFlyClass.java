package org.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.FlyClass;
import org.example.entity.Plan;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DtoFlyClass {
    private int id;
    private String name;
    private DtoPlan plan;

    public static DtoFlyClass toDto(FlyClass c) {
        var dto = new DtoFlyClass();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setPlan(DtoPlan.toDto(Plan.repo.get(c.getPlanId())));
        return dto;
    }

    public static FlyClass fromDto(DtoFlyClass dto) {
        var c = new FlyClass();
        c.setId(dto.getId());
        c.setName(dto.getName());
        c.setPlanId(dto.getPlan().getId());
        return c;
    }

}
