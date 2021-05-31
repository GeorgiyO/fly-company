package org.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.Cash;
import org.example.entity.Plan;
import org.example.entity.Ticket;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DtoTicket {
    private int id;
    private String number;
    private Double price;
    private DtoPlan plan;
    private DtoCash cash;

    public static DtoTicket toDto(Ticket t) {
        var dto = new DtoTicket();
        dto.setId(t.getId());
        dto.setNumber(t.getNumber());
        dto.setPrice(t.getPrice());
        dto.setPlan(DtoPlan.toDto(Plan.repo.get(t.getPlanId())));
        dto.setCash(DtoCash.toDto(Cash.repo.get(t.getCashId())));
        return dto;
    }

    public static Ticket fromDto(DtoTicket dto) {
        var t = new Ticket();
        t.setId(dto.getId());
        t.setNumber(dto.getNumber());
        t.setPrice(dto.getPrice());
        t.setPlanId(dto.getPlan().getId());
        t.setCashId(dto.getCash().getId());
        return t;
    }

}
