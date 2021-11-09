package com.giangvt.unittest.di;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.lang.reflect.Constructor;

public class ServiceTest {
    @Test
    void ensureJSR330Constructor() {
        int count = 0;
        Constructor<?>[] constructors = Service.class.getConstructors();
        for (Constructor<?> constructor : constructors) {
            Inject annotation = constructor.getAnnotation(Inject.class);
            if (annotation != null) {
                count++;
            }
        }
        assertEquals(1, count);
    }
}
