package com.anka.inheritance.asset;

import com.anka.inheritance.person.Person;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ASSET")
@Inheritance(strategy=InheritanceType.JOINED)
@Data
public class Asset {

    @Id
    @Column(name = "id", unique = true, nullable = false, precision = 10)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
}
