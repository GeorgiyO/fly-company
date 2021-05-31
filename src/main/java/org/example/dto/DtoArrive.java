package org.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.Arrive;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@ToString 
public class DtoArrive {
    private int id;
    private String name;
    private String address;

    public static DtoArrive toDto(Arrive arrive) {
        var dto = new DtoArrive();
        dto.setId(arrive.getId());
        dto.setName(arrive.getName());
        dto.setAddress(arrive.getAddress());
        return dto;
    }

    public static Arrive fromDto(DtoArrive dto) {
        var arrive = new Arrive();
        arrive.setId(dto.getId());
        arrive.setName(dto.getName());
        arrive.setAddress(dto.getAddress());
        return arrive;
    }
}
