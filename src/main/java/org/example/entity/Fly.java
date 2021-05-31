package org.example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.repository.FlyRepository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "fly")
@ToString
public class Fly {

    public static FlyRepository repo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_fly")
    private int id;

    @Size(max = 10)
    @NotBlank
    @Column(name = "number_fly")
    private String number;

    @NotNull
    @Column(name = "date_fly")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "id_arrive")
    private int arriveId;

    @Column(name = "id_live")
    private int liveId;
}
