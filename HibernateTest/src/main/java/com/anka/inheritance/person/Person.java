package com.anka.inheritance.person;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PERSON")
@Data
@EqualsAndHashCode(exclude = {"groups"})
@ToString(exclude = "groups")
public class Person {

    @Id
    @Column(name = "id", unique = true, nullable = false, precision = 10)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "persons")
    public Set<Group> groups;
}
