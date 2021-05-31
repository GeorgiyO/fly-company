package org.example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.repository.PlaceRepository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "place")
@ToString
public class Place {

    public static PlaceRepository repo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_place")
    private int id;

    @Size(max = 4)
    @NotBlank
    @Column(name = "number_place")
    private String number;

    @Column(name = "id_class")
    private int flyClassId;
}
