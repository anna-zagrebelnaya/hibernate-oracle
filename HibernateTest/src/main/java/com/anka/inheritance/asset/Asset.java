package com.anka.inheritance.asset;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ASSET")
@Inheritance(strategy=InheritanceType.JOINED)
@Data
public class Asset {

    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 19, scale = 0)
    private int id;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;
}
