package com.jacob.microservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "movimientos")
public class TransactionEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha")
    private LocalDateTime date;

    @Column(name = "valor")
    private Double value;

    @Column(name = "saldo")
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AccountEntity account;

}
