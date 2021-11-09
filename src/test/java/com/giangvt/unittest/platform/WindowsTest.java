package com.giangvt.unittest.platform;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

public class WindowsTest {
    @Test
    void testName() throws Exception {
        // only run on Linux
        Assumptions.assumeTrue(System.getProperty("os.name").contains("Windows"));
        Assertions.assertTrue(true);
    }
}
