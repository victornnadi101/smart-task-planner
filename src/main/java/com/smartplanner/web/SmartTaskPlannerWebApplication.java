package com.smartplanner.web;

import com.smartplanner.web.util.DatabaseSetup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartTaskPlannerWebApplication {
    public static void main(String[] args) {
        DatabaseSetup.initialize();
        SpringApplication.run(SmartTaskPlannerWebApplication.class, args);
    }

}
