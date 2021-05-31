package org.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.Arrive;
import org.example.entity.Fly;
import org.example.entity.Live;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DtoFly {
    private int id;
    private String number;
    private Date date;
    private DtoArrive arrive;
    private DtoLive live;

    public static DtoFly toDto(Fly f) {
        var dto = new DtoFly();
        dto.setId(f.getId());
        dto.setNumber(f.getNumber());
        dto.setDate(f.getDate());
        dto.setArrive(DtoArrive.toDto(Arrive.repo.get(f.getArriveId())));
        dto.setLive(DtoLive.toDto(Live.repo.get(f.getLiveId())));
        return dto;
    }

    public static Fly fromDto(DtoFly dto) {
        var f = new Fly();
        f.setId(dto.getId());
        f.setNumber(dto.getNumber());
        f.setDate(dto.getDate());
        f.setArriveId(dto.getArrive().getId());
        f.setLiveId(dto.getLive().getId());
        return f;
    }

}
