package com.giangvt.unittest.junit5;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.crypto.Data;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
public class MockitoVerifyTest {
    private Database database;

    @BeforeEach
    public void setUp() {
        database = new Database();
        database = mock(Database.class);
    }

    @Test
    public void testVerify() {
        // create and configure mock
        when(database.getUniqueId()).thenReturn(43);


        // call method testing on the mock with parameter 12
        database.setUniqueId(12);
        database.getUniqueId();
        database.getUniqueId();


        // now check if method testing was called with the parameter 12
        verify(database).setUniqueId(ArgumentMatchers.eq(12));

        // was the method called twice?
        verify(database, times(2)).getUniqueId();

        // other alternatives for verifiying the number of method calls for a method
        verify(database, never()).isAvailable();
        verify(database, never()).setUniqueId(13);
        verify(database, atLeastOnce()).setUniqueId(12);
        verify(database, atLeast(2)).getUniqueId();

        // more options are
        // times(numberOfTimes)
        // atMost(numberOfTimes)
        // This let's you check that no other methods where called on this object.
        // You call it after you have verified the expected method calls.
        verifyNoMoreInteractions(database);
    }
//    @Test
//    public void testVerify(@Mock Database database) {
//        // create and configure mock
//        when(database.getUniqueId()).thenReturn(43);
//
//
//        // call method testing on the mock with parameter 12
//        database.setUniqueId(12);
//        database.getUniqueId();
//        database.getUniqueId();
//
//
//        // now check if method testing was called with the parameter 12
//        verify(database).setUniqueId(ArgumentMatchers.eq(12));
//
//        // was the method called twice?
//        verify(database, times(2)).getUniqueId();
//
//        // other alternatives for verifiying the number of method calls for a method
//        verify(database, never()).isAvailable();
//        verify(database, never()).setUniqueId(13);
//        verify(database, atLeastOnce()).setUniqueId(12);
//        verify(database, atLeast(2)).getUniqueId();
//
//        // more options are
//        // times(numberOfTimes)
//        // atMost(numberOfTimes)
//        // This let's you check that no other methods where called on this object.
//        // You call it after you have verified the expected method calls.
//        verifyNoMoreInteractions(database);
//    }

}
