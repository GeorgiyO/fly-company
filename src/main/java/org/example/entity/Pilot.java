package org.example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.repository.PilotRepository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "pilot")
@ToString
public class Pilot {

    public static PilotRepository repo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_pilot")
    private int id;

    @Size(max = 45)
    @NotBlank
    @Column(name = "fio_pilot")
    private String fio;

    @NotBlank
    @Pattern(regexp = "[0-9]{10}")
    @Column(name = "tel_pilot")
    private String tel;

    @Column(name = "id_plan")
    private int planId;
}
