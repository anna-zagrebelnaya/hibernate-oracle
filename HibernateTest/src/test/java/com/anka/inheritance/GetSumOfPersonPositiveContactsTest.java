package com.anka.inheritance;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.anka.inheritance.GetSumOfPersonPositiveContacts.*;
import static org.junit.Assert.*;

public class GetSumOfPersonPositiveContactsTest {

    @BeforeClass
    public static void beforeClass() {
        setUp();
    }

    @Before
    public void before() {
        beginTransaction();
    }

    @After
    public void after() {
        commitTransaction();
    }

    @Test
    public void testAnonymousPerson() {
        assertEquals(11, calculateTotalPersonFriendsAndLikes(PERSON_ANONYMOUS_ID));
    }

    @Test
    public void testAnkaPerson() {
        assertEquals(7, calculateTotalPersonFriendsAndLikes(PERSON_ANKA_ID));
    }
}