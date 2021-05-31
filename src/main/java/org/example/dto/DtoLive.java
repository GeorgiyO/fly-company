package org.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.Live;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DtoLive {
    private int id;
    private String name;
    private String address;

    public static DtoLive toDto(Live l) {
        var dto = new DtoLive();
        dto.setId(l.getId());
        dto.setName(l.getName());
        dto.setAddress(l.getAddress());
        return dto;
    }

    public static Live fromDto(DtoLive dto) {
        var l = new Live();
        l.setId(dto.getId());
        l.setName(dto.getName());
        l.setAddress(dto.getAddress());
        return l;
    }

}
