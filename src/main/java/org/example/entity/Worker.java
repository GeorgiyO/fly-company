package org.example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.repository.WorkerRepository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "worker")
@ToString
public class Worker {

    public static WorkerRepository repo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_worker")
    private int id;

    @Size(max = 45)
    @NotBlank
    @Column(name = "fio_worker")
    private String fio;

    @NotBlank
    @Pattern(regexp = "[0-9]{10}")
    @Column(name = "tel_worker")
    private String tel;
}
