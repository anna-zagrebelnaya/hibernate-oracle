package com.anka.inheritance;

import org.junit.*;

import static com.anka.inheritance.ManyToManyRelationship.*;
import static org.junit.Assert.*;

public class ManyToManyRelationshipTest {

    @Before
    public void before() {
        setUp();
    }

    @Test
    public void testWhenRemovingGroupThenPersonsAreRemoved() {
        beginTransaction();
        removeGroup(getGroupById(GROUP_12_ID));
        assertNull(getPersonById(PERSON_1_ID));
        assertNull(getPersonById(PERSON_2_ID));
        assertNotNull(getPersonById(PERSON_3_ID));
        rollbackTransaction();
    }

    @Test
    public void testWhenRemovingPersonThenGroupIsNotRemoved() {
        beginTransaction();
        removePerson(getPersonById(PERSON_1_ID));
        assertNotNull(getGroupById(GROUP_12_ID));
        rollbackTransaction();
    }

}