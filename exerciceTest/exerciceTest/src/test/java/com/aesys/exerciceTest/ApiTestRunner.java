package com.aesys.exerciceTest;

import org.junit.Test;

public class ApiTestRunner {

    @Test
    public void runTestsMultipleTimes() throws InterruptedException {
        int iterations = 5;
        int delay = 5000; // 5000 ms = 5 seconds

        for (int i = 0; i < iterations; i++) {
            // Esegui i test
            org.junit.runner.JUnitCore.runClasses(UserApiTest.class);

            // Delay tra le iterazioni
            Thread.sleep(delay);
        }
    }
}
