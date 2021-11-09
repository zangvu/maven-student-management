package com.giangvt.unittest.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.giangvt.unittest.converter.ConverterUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class ParameterizedExampleTest {
    static int[][] data() {
        return new int[][]{{1, 2, 2}, {5, 3, 15}, {121, 4, 484}};
    }

    @ParameterizedTest(name = "{0} Celsius are {1} ")
    @CsvSource({"10, 50", "40, 104", "0, 32"})
    void ensureThatCelsiumConvertsToFahrenheit(int celsius, int fahrenheit) {
        assertEquals(fahrenheit, (int) ConverterUtil.convertCelsiusToFahrenheit(celsius));
    }

    @ParameterizedTest(name = "{index} called with: {0}")
    @MethodSource(value = "data")
    void testWithStringParameter(int[] data) {
        MyClass tester = new MyClass();
        int m1 = data[0];
        int m2 = data[1];
        int expected = data[2];
        assertEquals(expected, tester.multiply(m1, m2));
    }

    // class to be tested
    class MyClass {
        public int multiply(int i, int j) {
            return i * j;
        }
    }
}
