package com.giangvt.unittest.converter;

import static org.junit.Assert.assertEquals;

import com.giangvt.unittest.converter.ConverterUtil;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.stream.Stream;

public class ConverterUtilTest {

    int[][] celsiusFahrenheitMapping = new int[][]{{10, 50}, {40, 104}, {0, 32}};

    @TestFactory
    Stream<DynamicTest> ensureThatCelsiumConvertsToFahrenheit() {
        return Arrays.stream(celsiusFahrenheitMapping).map(entry -> {
            int celsius = entry[0];
            int fahrenheit = entry[1];
            return DynamicTest.dynamicTest(celsius + " Celsius ~ " + fahrenheit + " Fahrenheit",
                    () -> assertEquals(fahrenheit, (int) ConverterUtil.convertCelsiusToFahrenheit(celsius)));
        });

    }

    Stream<DynamicTest> ensureThatFahrenheitToCelsiumConverts() {
        Arrays.stream(celsiusFahrenheitMapping).map(item -> {
            int celsius = item[0];
            int fahrenheit = item[1];
            return DynamicTest.dynamicTest(fahrenheit + " Fahrenheit ~ " + celsius + " Celsius",
                    () -> assertEquals(fahrenheit, (int) ConverterUtil.convertFahrenheitToCelsius(fahrenheit)));
        });
        return null;
        // TODO Write a similar test fahrenheit to celsius
    }
}
