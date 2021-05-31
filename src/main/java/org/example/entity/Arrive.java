package org.example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.repository.ArriveRepository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "arrive")
@ToString 
public class Arrive {

    public static ArriveRepository repo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_arrive")
    private int id;

    @Size(max = 30)
    @NotBlank
    @Column(name = "name_arrive")
    private String name;

    @Size(max = 45)
    @NotBlank
    @Column(name = "address_arrive")
    private String address;
}
