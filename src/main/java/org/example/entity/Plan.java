package org.example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.repository.PlanRepository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "plan")
@ToString
public class Plan {

    public static PlanRepository repo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_plan")
    private int id;

    @Size(max = 10)
    @NotBlank
    @Column(name = "number_plan")
    private String number;

    @Size(max = 10)
    @NotBlank
    @Column(name = "kind_plan")
    private String kind;

    @Column(name = "id_worker")
    private int workerId;

    @Column(name = "id_fly")
    private int flyId;
}
