package org.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.Cash;
import org.example.entity.Client;
import org.example.entity.Ticket;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DtoClient {
    private int id;
    private String fio;
    private String password;
    private DtoTicket ticket;
    private DtoCash cash;

    public static DtoClient toDto(Client c) {
        var dto = new DtoClient();
        dto.setId(c.getId());
        dto.setFio(c.getFio());
        dto.setPassword(c.getPassword());
        dto.setTicket(DtoTicket.toDto(Ticket.repo.get(c.getTicketId())));
        dto.setCash(DtoCash.toDto(Cash.repo.get(c.getCashId())));
        return dto;
    }

    public static Client fromDto(DtoClient dto) {
        var c = new Client();
        c.setId(dto.getId());
        c.setFio(dto.getFio());
        c.setPassword(dto.getPassword());
        c.setCashId(dto.getTicket().getId());
        c.setTicketId(dto.getCash().getId());
        return c;
    }

}
