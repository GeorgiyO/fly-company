package org.example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.repository.ClientRepository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "client")
@ToString
public class Client {

    public static ClientRepository repo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_client")
    private int id;

    @Size(max = 45)
    @NotBlank
    @Column(name = "fio_client")
    private String fio;

    @Size(max = 45)
    @NotNull
    @Column(name = "password_client")
    private String password;

    @Column(name = "id_ticket")
    private int ticketId;

    @Column(name = "id_cash")
    private int cashId;
}
