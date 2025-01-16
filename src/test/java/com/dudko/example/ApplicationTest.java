package com.dudko.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * It is important not to forget to set environment variables for the tests
 */

@SpringBootTest
class ApplicationTest {

    @Test
    @DisplayName("SpringSecurityWithJWTApp: context loads")
    public void contextLoads() {}

}