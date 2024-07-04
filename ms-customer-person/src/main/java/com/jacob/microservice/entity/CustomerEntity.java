package com.jacob.microservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cliente")
public class CustomerEntity extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "contrasenia")
    private String password;

    @Column(name = "estado")
    private boolean state;

}
