package com.anka.inheritance;

import com.anka.inheritance.asset.Asset;
import com.anka.inheritance.asset.FBAsset;
import com.anka.inheritance.asset.Person;
import com.anka.inheritance.asset.TWAsset;
import com.anka.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import java.util.Locale;

public class InheritanceTest
{
    private static Session session;

    private static int PERSON_ANONYMOUS_ID = 10;
    private static int PERSON_ANKA_ID = 11;

    public static void main( String[] args )
    {
        System.out.println("Maven + Hibernate + Oracle");
        Locale.setDefault(Locale.ENGLISH); //need to avoid ORA-00604: error occurred at recursive SQL level 1
        session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        setUp();

        session.getTransaction().commit();
    }

    private static void setUp() {
        Person person = getOrCreatePerson(PERSON_ANONYMOUS_ID, "Anonymous");
        createFBAsset(100, person, 5);
        createTwitterAsset(200, person, 6);
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

    private static void createFBAsset(int id, Person person, int friends) {
        if (getAssetById(id) == null) {
            FBAsset fbAsset = new FBAsset();
            fbAsset.setId(id);
            fbAsset.setPerson(person);
            fbAsset.setFriendsOrLikes(friends);
            session.save(fbAsset);
        }
    }

    private static void createTwitterAsset(int id, Person person, int likes) {
        if (getAssetById(id) == null) {
            TWAsset twAsset = new TWAsset();
            twAsset.setId(id);
            twAsset.setPerson(person);
            twAsset.setFriendsOrLikes(likes);
            session.save(twAsset);
        }
    }

    private static Person getPersonById(int id) {
        return (Person) session.createCriteria(Person.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    private static Asset getAssetById(int id) {
        return (Asset) session.createCriteria(Asset.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }
}
