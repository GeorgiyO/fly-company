package org.example.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.repository.TicketRepository;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "ticket")
@ToString
public class Ticket {

    public static TicketRepository repo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_ticket")
    private int id;

    @Size(max = 10)
    @NotBlank
    @Column(name = "number_ticket")
    private String number;

    @NotNull
    @Column(name = "price_ticket")
    private Double price;

    @Column(name = "id_plan")
    private int planId;

    @Column(name = "id_cash")
    private int cashId;
}
