package org.example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.repository.LiveRepository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "live")
@ToString
public class Live {

    public static LiveRepository repo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_live")
    private int id;

    @Size(max = 30)
    @NotBlank
    @Column(name = "name_live")
    private String name;

    @Size(max = 45)
    @NotBlank
    @Column(name = "address_live")
    private String address;
}
