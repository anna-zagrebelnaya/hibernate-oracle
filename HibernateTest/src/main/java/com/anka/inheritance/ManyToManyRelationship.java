package com.anka.inheritance;

import com.anka.inheritance.person.Group;
import com.anka.inheritance.person.Person;
import com.anka.util.HibernateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.*;

public class ManyToManyRelationship
{
    private static Session session;

    static int PERSON_1_ID = 1;
    static int PERSON_2_ID = 2;
    static int PERSON_3_ID = 3;

    static int GROUP_12_ID = 12;
    static int GROUP_23_ID = 23;

    static Person person1;

    public static void main( String[] args )
    {
        System.out.println("Hibernate Many-To-Many and Inverse");
        setUp();

        beginTransaction();
        //removeGroup(getGroupById(GROUP_12_ID)); //removes all persons by cascade
        //removePerson(person1); //doesn't remove groups
        System.out.println("Group: " + getGroupById(GROUP_12_ID));
        System.out.println("Person: " + getPersonById(PERSON_1_ID));
        commitTransaction();
    }

    public static void setUp() {
        Locale.setDefault(Locale.ENGLISH); //need to avoid ORA-00604: error occurred at recursive SQL level 1
        openSession();
        beginTransaction();
        setUpInitialData();
        commitTransaction();
    }

    private static void openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    static void beginTransaction() {
        session.beginTransaction();
    }

    static void commitTransaction() {
        session.getTransaction().commit();
    }

    static void rollbackTransaction() {
        session.getTransaction().rollback();
    }

    private static void setUpInitialData() {
        person1 = getOrCreatePerson(PERSON_1_ID, "Person 1");
        Person person2 = getOrCreatePerson(PERSON_2_ID, "Person 2");
        Person person3 = getOrCreatePerson(PERSON_3_ID, "Person 3");

        Group group12 = getOrCreateGroup(GROUP_12_ID, "Group 12",
                new HashSet<Person>(Arrays.asList(person1, person2)));
        Group group23 = getOrCreateGroup(GROUP_23_ID, "Group 23",
                new HashSet<Person>(Arrays.asList(person2, person3)));
    }

    //db calls

    private static Person getOrCreatePerson(int id, String name) {
        Person person = getPersonById(id);
        if (person == null) {
            person = new Person();
            person.setId(id);
            person.setName(name);
            session.save(person);
        }
        return person;
    }

    private static Group getOrCreateGroup(int id, String name, Set<Person> persons) {
        Group group = getGroupById(id);
        if (group == null) {
            group = new Group();
            group.setId(id);
            group.setName(name);
            group.setPersons(persons);
            session.save(group);
        } else if (!CollectionUtils.isEqualCollection(group.getPersons(),persons)) {
            group.setPersons(persons);
            session.save(group);
        }
        return group;
    }

    static Person getPersonById(int id) {
        return (Person) session.createCriteria(Person.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    static void removePerson(Person person) {
        session.delete(person);
    }

    static Group getGroupById(int id) {
        return (Group) session.createCriteria(Group.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    static void removeGroup(Group group) {
        session.delete(group);
    }
}
