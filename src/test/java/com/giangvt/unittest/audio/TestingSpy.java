package com.giangvt.unittest.audio;

import static org.mockito.Mockito.doReturn;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestingSpy {
    @Spy
    List<String> spy = new LinkedList<>();

    @Test
    void testSpy() {
        doReturn("Certain String").when(spy).get(10000000);
        assertEquals("Certain String", spy.get(10000000));
    }
}
