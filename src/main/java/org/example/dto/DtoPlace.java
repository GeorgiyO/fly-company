package org.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.FlyClass;
import org.example.entity.Place;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DtoPlace {
    private int id;
    private String number;
    private DtoFlyClass flyClass;

    public static DtoPlace toDto(Place p) {
        var dto = new DtoPlace();
        dto.setId(p.getId());
        dto.setNumber(p.getNumber());
        dto.setFlyClass(DtoFlyClass.toDto(FlyClass.repo.get(p.getFlyClassId())));
        return dto;
    }

    public static Place fromDto(DtoPlace dto) {
        var p = new Place();
        p.setId(dto.getId());
        p.setNumber(dto.getNumber());
        p.setFlyClassId(dto.getFlyClass().getId());
        return p;
    }

}
