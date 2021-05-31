package org.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.Pilot;
import org.example.entity.Plan;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DtoPilot {
    private int id;
    private String fio;
    private String tel;
    private DtoPlan plan;

    public static DtoPilot toDto(Pilot p) {
        var dto = new DtoPilot();
        dto.setId(p.getId());
        dto.setFio(p.getFio());
        dto.setTel(p.getTel());
        dto.setPlan(DtoPlan.toDto(Plan.repo.get(p.getPlanId())));
        return dto;
    }

    public static Pilot fromDto(DtoPilot dto) {
        var p = new Pilot();
        p.setId(dto.getId());
        p.setFio(dto.getFio());
        p.setTel(dto.getTel());
        p.setPlanId(dto.getPlan().getId());
        return p;
    }

}
