package com.anka.inheritance.asset;

import javax.persistence.*;

@Entity
@Table(name = "ASSET")
@Inheritance(strategy=InheritanceType.JOINED)
public class Asset {

    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 19, scale = 0)
    private int id;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
