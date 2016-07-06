package com.anka.inheritance;

import com.anka.inheritance.asset.Asset;
import com.anka.inheritance.asset.FBAsset;
import com.anka.inheritance.person.Person;
import com.anka.inheritance.asset.TWAsset;
import com.anka.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;

import java.util.Locale;

public class GetSumOfPersonPositiveContacts
{
    private static Session session;

    static int PERSON_ANONYMOUS_ID = 10;
    static int PERSON_ANKA_ID = 11;

    public static void main( String[] args )
    {
        System.out.println("Maven + Hibernate + Oracle");
        setUp();

        beginTransaction();
        Object total = calculateTotalPersonFriendsAndLikes(PERSON_ANONYMOUS_ID);
        System.out.println(total);
        commitTransaction();

        beginTransaction();
        Object total2 = calculateTotalPersonFriendsAndLikes(PERSON_ANKA_ID);
        System.out.println(total2);
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

    private static void setUpInitialData() {
        Person person = getOrCreatePerson(PERSON_ANONYMOUS_ID, "Anonymous");
        createFBAsset(100, person, 5);
        createTwitterAsset(200, person, 6);

        Person person2 = getOrCreatePerson(PERSON_ANKA_ID, "Anka");
        createFBAsset(102, person2, 3);
        createTwitterAsset(202, person2, 4);
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
            fbAsset.setFriends(friends);
            session.save(fbAsset);
        }
    }

    private static void createTwitterAsset(int id, Person person, int likes) {
        if (getAssetById(id) == null) {
            TWAsset twAsset = new TWAsset();
            twAsset.setId(id);
            twAsset.setPerson(person);
            twAsset.setLikes(likes);
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

    static Object calculateTotalPersonFriendsAndLikes(int personId) {
        return session.createCriteria(Asset.class)
                .add(Restrictions.eq("person.id", personId))
                .setProjection(Projections.projectionList()
                        .add(Projections.sqlGroupProjection(
                                "sum(this_1_.FRIENDS) + sum(this_2_.LIKES) as total", //this_1, this_2 - tables of subclasses, there is no possibility to push their aliases into projection
                                "{alias}.PERSON_ID",
                                new String[]{"total"},
                                new Type[]{IntegerType.INSTANCE}
                        )))
                .uniqueResult();
    }
}
