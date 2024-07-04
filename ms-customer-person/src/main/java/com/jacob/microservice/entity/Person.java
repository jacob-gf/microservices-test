package com.jacob.microservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@Getter @Setter
public class Person {
    @Column(name = "nombre")
    private String name;
    @Column(name = "genero")
    private String gender;
    @Column(name = "edad")
    private int age;
    @Column(name = "identificacion")
    private String identification;
    @Column(name = "direccion")
    private String address;
    @Column(name = "telefono")
    private String phone;
}
