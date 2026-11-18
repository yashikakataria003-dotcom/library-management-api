package com.kiet.studentmanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentmanagementApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(StudentmanagementApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StudentmanagementApplication.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("Library Book Management System Started");
        logger.info("Loading Book Catalogue...");
        logger.info("Book Management REST API is Ready");
    }
}
