package com.jacob.microservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "cuenta")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_cuenta")
    private String accountNumber;
    @Column(name = "tipo_cuenta")
    private String accountType;
    @Column(name = "saldo_inicial")
    private Double initialBalance;
    @Column(name = "estado")
    private boolean state;
    @Column(name = "cliente_id")
    private Long customerId;

}
