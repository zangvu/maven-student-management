package com.giangvt.unittest.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

public class MockitoSpyWithListTest {

    // demonstrates of the spy function
    @Test
    void ensureSpyForListWorks() {
        var list = new ArrayList<String>();
        var spiedList = spy(list);

        // cai nay quang exception
        // when(spiedList.get(99)).thenReturn("42");
        doReturn("42").when(spiedList).get(99);
        String value = (String) spiedList.get(99);

        assertEquals("42", value);
    }

    @Spy
    List<String> spy = new LinkedList<>();

    @BeforeEach
    void setup() {
        // Alternative way of creating a spy
        // List<String> list = new LinkedList<>();
        // List<String> spy = spy(list);
    }

    @Test
    void testLinkedListSpyCorrect() {

        // when(spy.get(0)).thenReturn("foo");
        // would not work as the delegate it called so spy.get(0)
        // throws IndexOutOfBoundsException (list is still empty)

        // you have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);

        assertEquals("foo", spy.get(0));
    }
}
