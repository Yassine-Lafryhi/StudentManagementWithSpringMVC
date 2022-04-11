package com.student.management;

import com.student.management.security.services.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner saveUsers(SecurityService securityService) {
        return args -> {
            securityService.saveNewUser("omar", "12345", "12345");
            securityService.saveNewUser("hafsa", "12345", "12345");
            securityService.saveNewUser("haitham", "12345", "12345");

            securityService.saveNewRole("USER", "user");
            securityService.saveNewRole("ADMIN", "admin");

            securityService.addRoleToUser("omar", "ADMIN");
            securityService.addRoleToUser("hafsa", "USER");
            securityService.addRoleToUser("haitham", "USER");
        };
    }


}
