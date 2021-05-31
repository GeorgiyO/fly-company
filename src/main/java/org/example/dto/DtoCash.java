package org.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.Cash;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DtoCash {
    private int id;
    private String number;

    public static DtoCash toDto(Cash arrive) {
        var dto = new DtoCash();
        dto.setId(arrive.getId());
        dto.setNumber(arrive.getNumber());
        return dto;
    }

    public static Cash fromDto(DtoCash dto) {
        var cash = new Cash();
        cash.setId(dto.getId());
        cash.setNumber(dto.getNumber());
        return cash;
    }
}
