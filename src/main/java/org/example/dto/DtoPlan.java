package org.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.Fly;
import org.example.entity.Plan;
import org.example.entity.Worker;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DtoPlan {
    private int id;
    private String number;
    private String kind;
    private DtoWorker worker;
    private DtoFly fly;

    public static DtoPlan toDto(Plan p) {
        var dto = new DtoPlan();
        dto.setId(p.getId());
        dto.setNumber(p.getNumber());
        dto.setKind(p.getKind());
        dto.setWorker(DtoWorker.toDto(Worker.repo.get(p.getWorkerId())));
        dto.setFly(DtoFly.toDto(Fly.repo.get(p.getFlyId())));
        return dto;
    }

    public static Plan fromDto(DtoPlan dto) {
        var p = new Plan();
        p.setId(dto.getId());
        p.setNumber(dto.getNumber());
        p.setKind(dto.getKind());
        p.setWorkerId(dto.getWorker().getId());
        p.setFlyId(dto.getFly().getId());
        return p;
    }

}
