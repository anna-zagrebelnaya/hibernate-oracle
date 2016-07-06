package com.anka.inheritance.person;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PERSON_GROUP")
@Data
public class Group {

    @Id
    @Column(name = "id", unique = true, nullable = false, precision = 10)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    //owner of relationship is group
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "PERSONS_IN_GROUPS",
            joinColumns = {
                    @JoinColumn(name = "group_id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "person_id", nullable = false, updatable = false)
            })
    private Set<Person> persons;

}
