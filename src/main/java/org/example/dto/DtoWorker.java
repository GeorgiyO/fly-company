package org.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.entity.Worker;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DtoWorker {
    private int id;
    private String fio;
    private String tel;

    public static DtoWorker toDto(Worker w) {
        var dto = new DtoWorker();
        dto.setId(w.getId());
        dto.setFio(w.getFio());
        dto.setTel(w.getTel());
        return dto;
    }

    public static Worker fromDto(DtoWorker dto) {
        var w = new Worker();
        w.setId(dto.getId());
        w.setFio(dto.getFio());
        w.setTel(dto.getTel());
        return w;
    }

}
