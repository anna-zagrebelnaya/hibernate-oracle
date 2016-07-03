package com.anka.inheritance.asset;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
@Data
public class Person {

    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 19, scale = 0)
    private int id;

    @Column(name = "name")
    private String name;

}
