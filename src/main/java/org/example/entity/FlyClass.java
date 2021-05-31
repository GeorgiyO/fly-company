package org.example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.repository.FlyClassRepository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "class")
@ToString
public class FlyClass {

    public static FlyClassRepository repo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_class")
    private int id;

    @Column(name = "name_class")
    @NotBlank
    @Size(max = 10)
    private String name;

    @Column(name = "id_plan")
    private int planId;
}
